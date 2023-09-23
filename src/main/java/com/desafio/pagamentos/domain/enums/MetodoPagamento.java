package com.desafio.pagamentos.domain.enums;

public enum MetodoPagamento {
    PIX {
        @Override
        public boolean deveInformarNumeroDeCartao() {
            return false;
        }
    },
    BOLETO {
        @Override
        public boolean deveInformarNumeroDeCartao() {
            return false;
        }
    },
    CREDITO {
        @Override
        public boolean deveInformarNumeroDeCartao() {
            return true;
        }
    },
    DEBITO {
        @Override
        public boolean deveInformarNumeroDeCartao() {
            return true;
        }
    };

    public abstract boolean deveInformarNumeroDeCartao();
}
