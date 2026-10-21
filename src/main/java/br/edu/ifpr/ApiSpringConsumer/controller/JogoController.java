package br.edu.ifpr.ApiSpringConsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.ApiSpringConsumer.model.EstadoJogo;
import br.edu.ifpr.ApiSpringConsumer.model.Partida;
import br.edu.ifpr.ApiSpringConsumer.model.Personagem;
import br.edu.ifpr.ApiSpringConsumer.model.Pista;
import br.edu.ifpr.ApiSpringConsumer.repository.PartidaRepository;
import br.edu.ifpr.ApiSpringConsumer.service.RickAndMortyService;
import jakarta.servlet.http.HttpSession;

@Controller
public class JogoController {

    private final RickAndMortyService rickAndMortyService;
    private final PartidaRepository partidaRepository;

    @Autowired/*
    é uma anotação do Spring que indica que o construtor deve ser usado para injeção de dependência.
    Injeção de dependência é um padrão de design que permite que uma classe receba suas dependências de forma externa, em vez de criá-las internamente.
    Isso promove um acoplamento mais fraco entre classes e facilita a manutenção e os testes.
    No caso do JogoController, o Spring irá automaticamente fornecer instâncias de RickAndMortyService e PartidaRepository quando criar uma instância do controlador,
    garantindo que ele tenha acesso aos serviços necessários para funcionar corretamente
    */
    public JogoController(RickAndMortyService rickAndMortyService, PartidaRepository partidaRepository) {
        this.rickAndMortyService = rickAndMortyService;
        this.partidaRepository = partidaRepository;
    }

    /**
     * Tela inicial da aplicação (home.html).
     */
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");
        model.addAttribute("temPartidaAtiva", estado != null && "JOGANDO".equals(estado.getStatus()));
        return "home";
    }

    /**
     * Inicia uma nova rodada sorteando um personagem da API e salvando na sessão.
     */
    @GetMapping("/jogar")
    public String iniciarJogo(HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Personagem personagem = rickAndMortyService.sortearPersonagem();
            List<Pista> pistas = rickAndMortyService.gerarPistas(personagem);

            EstadoJogo estado = new EstadoJogo(personagem, pistas);
            session.setAttribute("estadoJogo", estado);

            return "redirect:/perguntas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao conectar com a API do Rick and Morty: " + e.getMessage());
            return "redirect:/";
        }
    }

    /**
     * Tela principal do jogo com as pistas e envio de palpites (perguntas.html).
     */
    @GetMapping("/perguntas")
    public String perguntas(HttpSession session, Model model) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");

        if (estado == null) {
            return "redirect:/jogar";
        }

        if (!"JOGANDO".equals(estado.getStatus())) {
            return "redirect:/resultado";
        }

        model.addAttribute("estado", estado);
        model.addAttribute("pistaAtual", estado.getPistaAtual());
        model.addAttribute("pistasReveladas", estado.getPistasReveladas());
        model.addAttribute("tentativasRestantes", estado.getTentativasRestantes());
        model.addAttribute("totalTentativas", estado.getTotalTentativas());
        model.addAttribute("palpitesErrados", estado.getPalpitesErrados());
        model.addAttribute("temMaisPistas", estado.temMaisPistas());

        return "perguntas";
    }

    /**
     * Processa o palpite enviado pelo usuário.
     */
    @PostMapping("/palpite")
    public String processarPalpite(@RequestParam("palpite") String palpite,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");

        if (estado == null || !"JOGANDO".equals(estado.getStatus())) {
            return "redirect:/";
        }

        if (palpite == null || palpite.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemAviso", "Por favor, digite um palpite válido!");
            return "redirect:/perguntas";
        }

        boolean acertou = estado.validarPalpite(palpite);

        if (acertou) {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Parabéns! Você acertou!");
            return "redirect:/resultado";
        } else {
            if ("DERROTA".equals(estado.getStatus())) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Você esgotou todas as tentativas!");
                return "redirect:/resultado";
            }
            redirectAttributes.addFlashAttribute("mensagemAviso", "Palpite incorreto! Uma nova pista foi liberada.");
            return "redirect:/perguntas";
        }
    }

    /**
     * Revela uma pista adicional sem gastar palpite.
     */
    @GetMapping("/pedir-pista")
    public String pedirPista(HttpSession session, RedirectAttributes redirectAttributes) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");

        if (estado != null && "JOGANDO".equals(estado.getStatus())) {
            if (estado.temMaisPistas()) {
                estado.revelarProximaPista();
            } else {
                redirectAttributes.addFlashAttribute("mensagemAviso", "Todas as pistas já foram reveladas!");
            }
        }
        return "redirect:/perguntas";
    }

    /**
     * Usuário opta por desistir da partida.
     */
    @GetMapping("/desistir")
    public String desistir(HttpSession session) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");
        if (estado != null) {
            estado.desistir();
        }
        return "redirect:/resultado";
    }

    /**
     * Tela final com animação de resultado, dados do personagem e opção de salvar no banco (resultado.html).
     */
    @GetMapping("/resultado")
    public String resultado(HttpSession session, Model model) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");

        if (estado == null) {
            return "redirect:/";
        }

        model.addAttribute("estado", estado);
        model.addAttribute("personagem", estado.getPersonagem());
        model.addAttribute("isVitoria", "VITORIA".equals(estado.getStatus()));
        model.addAttribute("mensagem", estado.getMensagemResultado());

        return "resultado";
    }

    /**
     * Salva o registro da partida no banco de dados via JPA.
     */
    @PostMapping("/salvar-partida")
    public String salvarPartida(@RequestParam(value = "observacao", required = false) String observacao,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        EstadoJogo estado = (EstadoJogo) session.getAttribute("estadoJogo");

        if (estado != null && estado.getPersonagem() != null) {
            Partida partida = new Partida();
            partida.setNomePersonagem(estado.getPersonagem().getName());
            partida.setImagemPersonagem(estado.getPersonagem().getImage());
            partida.setStatusResultado("VITORIA".equals(estado.getStatus()) ? "Vitória" : "Derrota");
            partida.setTentativasUsadas(estado.getTotalTentativas() - estado.getTentativasRestantes());
            partida.setPistasUsadas(estado.getPistasReveladas().size());
            partida.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : "Partida finalizada.");

            partidaRepository.save(partida);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Partida salva com sucesso no banco de dados!");
        }

        return "redirect:/registros";
    }
}
