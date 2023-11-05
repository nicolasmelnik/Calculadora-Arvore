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
