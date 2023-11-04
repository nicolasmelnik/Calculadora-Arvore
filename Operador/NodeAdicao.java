// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

package Operador;

import Operando.*;

public class NodeAdicao extends Node {

    public NodeAdicao() {
        super("+");
    }

    @Override
    public float visitar() {
        return getLeft().visitar() + getRight().visitar();
    }
}