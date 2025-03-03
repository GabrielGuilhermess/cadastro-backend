package com.softplan.cadastro_backend.domain.model;

import com.softplan.cadastro_backend.domain.enums.SexoEnum;
import com.softplan.cadastro_backend.utils.ConstantsTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários para o enum {@link com.softplan.cadastro_backend.domain.enums.SexoEnum}.
 */
public class SexoEnumTest {

    /**
     * Verifica se o enum possui a quantidade correta de valores.
     */
    @Test
    public void testEnumValuesCount() {
        SexoEnum[] valores = SexoEnum.values();
        assertEquals(4, valores.length, "SexoEnum deve ter 4 valores.");
    }

    /**
     * Valida se as descrições dos valores do enum estão corretas.
     */
    @Test
    public void testEnumDescriptions() {
        assertEquals(ConstantsTestData.DESCRICAO_SEXO_NAO_DEFINIDO, SexoEnum.NAO_DEFINIDO.getDescricao(), "Descrição incorreta para NAO_DEFINIDO.");
        assertEquals(ConstantsTestData.DESCRICAO_SEXO_MASCULINO, SexoEnum.MASCULINO.getDescricao(), "Descrição incorreta para MASCULINO.");
        assertEquals(ConstantsTestData.DESCRICAO_SEXO_FEMININO, SexoEnum.FEMININO.getDescricao(), "Descrição incorreta para FEMININO.");
        assertEquals(ConstantsTestData.DESCRICAO_SEXO_OUTROS, SexoEnum.OUTROS.getDescricao(), "Descrição incorreta para OUTROS.");
    }
}
