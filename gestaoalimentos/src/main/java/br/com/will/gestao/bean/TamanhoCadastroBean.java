package br.com.will.gestao.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.will.gestao.entidade.Produto;
import br.com.will.gestao.entidade.Tamanho;
import br.com.will.gestao.servico.EmpresaServico;
import br.com.will.gestao.servico.ProdutoServico;
import br.com.will.gestao.servico.TamanhoServico;

@Named
@ViewScoped
public class TamanhoCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private TamanhoServico tamanhoServico;
	@EJB
	private EmpresaServico empresaServico;
	@EJB
	private ProdutoServico produtoServico;
	
	private List<Produto> produtos;
	private Tamanho tamanho;
	private Integer idProduto;
	
	private String preco = "0";

	
	@PostConstruct
	public void inicializar() {
		getLog().info("Criando Produto Cadastro Bean");
		try {
			if (tamanho == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");
				if (idParam != null && !idParam.equals("")) {
					try {
						tamanho = tamanhoServico.obterCompletoPorId(Integer.parseInt(idParam));
					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Tamanho");
					}
				}
				if (this.tamanho == null) {
					this.tamanho = new Tamanho();
				} else {
					if (tamanho.getProduto() != null) {
						idProduto = tamanho.getProduto().getId();
					}
					this.preco = tamanho.getPreco().toString();
				}
			}
			produtos = produtoServico.obterTodos("sequencia");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {
			this.tamanho.setEmpresa(getLoginBean().getEmpresa());
			if (idProduto != null && idProduto > 0) {
				Produto produto = produtoServico.obterPorId(idProduto);
				this.tamanho.setProduto(produto);
			}
			
			if (this.preco != null && !this.preco.isEmpty()) {
				this.tamanho.setPreco(new BigDecimal(this.preco));
			}
			
			if (this.tamanho.getId() != null) {
				tamanhoServico.alterar(this.tamanho);
			} else {
				this.tamanho = tamanhoServico.salvar(this.tamanho);
			}
			
			return "tamanhos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
			return null;
		}
	}
	
	public Tamanho getTamanho() {
		return tamanho;
	}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
}