package Operador;

import Operando.*;

public class NodeDivisao extends Node {

    public NodeDivisao() {
        super("/");
    }

    @Override
    public float visitar() {
        return getLeft().visitar() / getRight().visitar();
    }
}
