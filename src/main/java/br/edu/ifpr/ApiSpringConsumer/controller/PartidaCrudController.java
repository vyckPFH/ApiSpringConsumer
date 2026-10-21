package br.edu.ifpr.ApiSpringConsumer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.ApiSpringConsumer.model.Partida;
import br.edu.ifpr.ApiSpringConsumer.repository.PartidaRepository;

/**
 * Controller responsável pelo CRUD completo de registros de partidas,
 * atendendo rigorosamente à exigência de Cadastrar, Listar, Editar e Excluir registros.
 */
@Controller
public class PartidaCrudController {

    private final PartidaRepository partidaRepository;

    @Autowired 
    public PartidaCrudController(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    /**
     * 1. LISTAR REGISTROS (R)
     */
    @GetMapping("/registros")
    public String listarRegistros(Model model) {
        List<Partida> registros = partidaRepository.findAllByOrderByDataRegistroDesc();
        model.addAttribute("registros", registros);
        model.addAttribute("novaPartida", new Partida());
        return "registros";
    }

    /**
     * 2. CADASTRAR / SALVAR REGISTRO (C & U)
     */
    @PostMapping("/registros/salvar")
    public String salvarRegistro(@ModelAttribute("partida") Partida partida, RedirectAttributes redirectAttributes) {
        if (partida.getNomePersonagem() == null || partida.getNomePersonagem().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "O nome do personagem é obrigatório!");
            return "redirect:/registros";
        }

        if (partida.getImagemPersonagem() == null || partida.getImagemPersonagem().trim().isEmpty()) {
            partida.setImagemPersonagem("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        }

        boolean ehEdicao = partida.getId() != null;
        partidaRepository.save(partida);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                ehEdicao ? "Registro atualizado com sucesso!" : "Novo registro cadastrado com sucesso!");

        return "redirect:/registros";
    }

    /**
     * 3. FORMULÁRIO DE EDIÇÃO (U)
     */
    @GetMapping("/registros/editar/{id}")
    public String editarRegistro(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Partida> optPartida = partidaRepository.findById(id);
        if (optPartida.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Registro não encontrado!");
            return "redirect:/registros";
        }

        model.addAttribute("partidaEdicao", optPartida.get());
        model.addAttribute("registros", partidaRepository.findAllByOrderByDataRegistroDesc());
        return "registros";
    }

    /**
     * 4. EXCLUIR REGISTRO (D)
     */
    @GetMapping("/registros/excluir/{id}")
    public String excluirRegistro(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (partidaRepository.existsById(id)) {
            partidaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Registro excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Registro não encontrado para exclusão!");
        }
        return "redirect:/registros";
    }
}
