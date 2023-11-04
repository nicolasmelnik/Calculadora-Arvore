// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

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