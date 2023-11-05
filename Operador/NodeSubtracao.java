package Operador;

import Operando.*;

public class NodeSubtracao extends Node {

    public NodeSubtracao() {
        super("-");
    }

    @Override
    public float visitar() {
        return getLeft().visitar() - getRight().visitar();
    }
}
