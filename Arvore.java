package TrabGA;

public class Arvore {

	public No raiz;

	public Arvore() {
		raiz = null;
	}

	public boolean inserir(int valor) {
		No repetido = buscar(valor);
		if (repetido == null) {
			raiz = inserir(valor, raiz);
			System.out.println(imprimir(raiz));
			return true;
		} else {
			System.out.println("Valor ja consta na arvore!");
			return false;
		}
	}

	public No inserir(int valor, No no) {
		if (no == null) {
			no = new No(valor);
		} else if (valor < no.item) {
			no.esquerda = inserir(valor, no.esquerda);
		} else if (valor > no.item) {
			no.direita = inserir(valor, no.direita);
		}

		no = balancear(no);
		return no;
	}
	
	public void remover(int valor) {
        raiz = remover(raiz, valor);
        System.out.println(imprimir(raiz));
    }

	public No remover(No no, int valor) {
		if (no == null) {
			return no;
		} else if (no.item > valor) {
			no.esquerda = remover(no.esquerda, valor);
		} else if (no.item < valor) {
			no.direita = remover(no.direita, valor);
		} else {
			if (no.esquerda == null || no.direita == null) {
				if(no.esquerda == null) {
					no = no.direita;
				} else {
					no = no.esquerda;
				}
			} else {
				No ultimoEsquerda = ultimoEsquerda(no.direita);
				no.item = ultimoEsquerda.item;
				no.direita = remover(no.direita, no.item);
			}
		}
		if (no != null) {
			no = balancear(no);
		}
		return no;
	}

	private No ultimoEsquerda(No no) {
		No atual = no;
		/* loop down to find the leftmost leaf */
		while (atual.esquerda != null) {
			atual = atual.esquerda;
		}
		return atual;
	}

	public static String imprimir(No aux) {
		String retorno;
		retorno = "(";
		if (aux != null) {
			retorno += "C" + aux.item;
			retorno += imprimir(aux.esquerda);
			retorno += imprimir(aux.direita);
		}
		retorno += ")";
		return retorno;
	}

	// RETORNA O CAMINHO ATE CHEGAR NO NUMERO BUSCADO
	public No buscarPrint(int valor) {

		if (raiz == null) {
			System.out.println("A raiz é nula");
			return null;
		}
		No atual = raiz;

		if (atual.item == valor) {
			System.out.println("\nO elemento consta");
			return atual;
		} else {

			while (valor != atual.item) {

				System.out.print(atual.item + " - ");

				if (valor < atual.item) {
					atual = atual.esquerda;
					if (atual == null) {
						System.out.println("\nO elemento nÃ£o consta");
						return null;
					}
				} else {
					atual = atual.direita;
					if (atual == null) {
						System.out.println("\nO elemento nÃ£o consta");
						return null;
					}
				}

			}
		}
		System.out.println("\nO elemento consta na arvore");
		return atual;

	}

	// RETORNA O NO DO VALOR BUSCADO
	public No buscar(int valor) {
		if (raiz == null) {
			return null;
		}
		No atual = raiz;

		if (atual.item == valor) {
			return atual;
		} else {

			while (valor != atual.item) {
				if (valor < atual.item) {
					atual = atual.esquerda;
					if (atual == null) {
						return null;
					}
				} else {
					atual = atual.direita;
					if (atual == null) {
						return null;
					}
				}

			}
		}
		return atual;
	}

	// METODO PARA IMPRIMIR EM ORDEM
	public void imprimirOrdem(No atual) {
		if (atual != null) {
			imprimirOrdem(atual.esquerda);
			System.out.print(atual.item + " ");
			imprimirOrdem(atual.direita);
		}
	}

	// METODO PARA IMPRIMIR PRE ORDEM
	public void imprimirPreOrdem(No atual) {
		if (atual != null) {
			System.out.print(atual.item + " ");
			imprimirPreOrdem(atual.esquerda);
			imprimirPreOrdem(atual.direita);
		}
	}

	// METODO PARA IMPRIMIR POS ORDEM
	public void imprimirPosOrdem(No atual) {
		if (atual != null) {
			imprimirPosOrdem(atual.esquerda);
			imprimirPosOrdem(atual.direita);
			System.out.print(atual.item + " ");
		}
	}

	// IMPRIME NA TELA OS CAMINHOS EM ORDEM, PRE, E POS
	public void exibir() {
		System.out.println("Impressão em Ordem: ");
		imprimirOrdem(raiz);
		System.out.println("\nImpressão em Pré-Ordem: ");
		imprimirPreOrdem(raiz);
		System.out.println("\nImpressão em Pós-Ordem: ");
		imprimirPosOrdem(raiz);
	}

// ------------------------------------- FUNCOES AUXILIARES -------------------------------------------------------//
	public int altura(No no) {
		if (no == null) {
			return -1;
		} else {
			return no.altura;
		}

	}

	public int fatorBalanceamento(No no) {
		return altura(no.esquerda) - altura(no.direita);
	}

	public No balancear(No no) {
		if (fatorBalanceamento(no) == 2) {
			if (fatorBalanceamento(no.esquerda) > 0)
				no = rotacaoDireita(no);
			else
				no = duplaRotacaoDireita(no);
		} else if (fatorBalanceamento(no) == -2) {
			if (fatorBalanceamento(no.direita) < 0)
				no = rotacaoEsquerda(no);
			else
				no = duplaRotacaoEsquerda(no);
		}
		no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
		return no;
	}

	// ROTACAO DIREITA
	public No rotacaoDireita(No no) {
		No n = no.esquerda;
		no.esquerda = n.direita;
		n.direita = no;
		no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
		n.altura = Math.max(altura(n.esquerda), no.altura) + 1;
		return n;
	}

	// ROTACAO ESQUERDA
	public No rotacaoEsquerda(No no) {
		No n = no.direita;
		no.direita = n.esquerda;
		n.esquerda = no;
		no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
		n.altura = Math.max(altura(n.direita), no.altura) + 1;
		return n;
	}

	// DUPLA ROTACAO DIREITA
	public No duplaRotacaoDireita(No no) {
		no.esquerda = rotacaoEsquerda(no.esquerda);
		return rotacaoDireita(no);
	}

	// DUPLA ROTACAO ESQUERDA
	public No duplaRotacaoEsquerda(No no) {
		no.direita = rotacaoDireita(no.direita);
		return rotacaoEsquerda(no);
	}

}