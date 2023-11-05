package Operando;

public class NodeOperando extends Node {

    public NodeOperando(String valor) {
        super(valor);
    }

    @Override
    public float visitar() {
        return Float.parseFloat(super.getData());
    }
}
