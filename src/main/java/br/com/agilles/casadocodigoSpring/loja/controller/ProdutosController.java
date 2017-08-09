package br.com.agilles.casadocodigoSpring.loja.controller;

import br.com.agilles.casadocodigoSpring.loja.dao.ProdutoDao;
import br.com.agilles.casadocodigoSpring.loja.infra.FileSaver;
import br.com.agilles.casadocodigoSpring.loja.models.Produto;
import br.com.agilles.casadocodigoSpring.loja.models.TipoPreco;
import br.com.agilles.casadocodigoSpring.loja.validators.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoDao produtoDao;
    @Autowired
    private FileSaver fileSaver;
    private String path;

    /**
     * Observação: O Binder, por assim dizer, é o responsável por conectar duas coisas. Por exemplo, os dados do formulário com o objeto da classe Produto
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProdutoValidation());
    }

    /**
     * @return
     */
    @RequestMapping("/form")
    public ModelAndView form(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(sumario.getOriginalFilename());


        if (bindingResult.hasErrors()) {
            return form(produto);
        }
        String path = fileSaver.write("sumarioFiles", sumario);
        produto.setSumarioPath(path);
        produtoDao.gravar(produto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");

        return new ModelAndView("redirect:/produtos");

    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
        List<Produto> produtos = produtoDao.listar();
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    @RequestMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
        Produto produto = produtoDao.find(id);
        modelAndView.addObject("produto",produto);
        return modelAndView;
    }
}
