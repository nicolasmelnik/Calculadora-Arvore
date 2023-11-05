package Operador;

import Operando.*;

public class NodeMultiplicacao extends Node {

    public NodeMultiplicacao() {
        super("*");
    }

    @Override
    public float visitar() {
        return getLeft().visitar() * getRight().visitar();
    }
}
