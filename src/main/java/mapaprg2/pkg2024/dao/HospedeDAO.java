/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapaprg2.pkg2024.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mapaprg2.pkg2024.model.Hospede;

/** 12/09/2024
 *
 * @author Ricardo Gabriel Gomes
 * 
 * 1 - O analista de segurança da equipe reparou que na classe HospedeDAO, no 
 * método buscarHospedePorCpf, o parâmetro está sendo concatenado na String sql, 
 * não sendo uma prática segura. Descreva qual alternativa pode resolver essa 
 * possível vulnerabilidade, justificando a resposta.
 * R: Para resolver o problema de segurança utilizei o método setString() para 
 * inserir o parâmetro, que no caso é a string cpf, na instrução SQL.
 *
 *2 - O usuário reclamou que o sistema está com bug, o erro apresentado no 
 * console é o seguinte: java.sql.SQLSyntaxErrorException: Unknown column 'fone'
 * in 'field list’. Identifique o trecho do código que deve ser corrigido para 
 * solucionar este bug, justificando seu ajuste.
 * R: O trecho de código está na classe HospedeDAO, no método cadastraHospede, 
 * na linha onde é inserido a 'String sql = "insert into hospede (nome, cpf, 
 * fone, email) values (?,?,?,?)";' o parametro fone está incorreto porque não 
 * existe uma coluna com esse nome na tabela hospede, o correto é "telefone" 
 * então eu apenas mudei corrigi.
 *
 *3 - A estrutura de pastas do sistema atual contém uma camada de acesso ao 
 * banco de dados e às telas, entretanto algumas regras de negócio estão sendo 
 * inseridas na própria tela na classe GerenciarHospede. Esta não é uma boa 
 * prática de implementação, pois se o sistema cresce as regras ficam vinculadas
 * às telas, tendo que esporadicamente repetir regras em diferentes telas. Para 
 * resolver tal problema, que alternativa poderia ser utilizada nesse projeto 
 * para garantir que as regras de negócio fiquem isoladas? Descreva e justifique
 * a sua solução.
 * R: Acredito que a melhor solução para esse tipo de problema seja a criação de
 * uma interface e classe para gerenciar mais facilmente as regras de negócio desse
 * projeto padronizando a estrutura que as telas vão seguir e a lógica, poderia
 * criar funções default na interface e colocar e inserir a lógica geral que é usada
 * nas telas e quando for criar uma nova tela não precisamos repetir desnecessariamente
 * as funções.
 */
public class HospedeDAO {
    public void cadastraHospede(Hospede hospede) throws ClassNotFoundException {
        String sql = "insert into hospede (nome, cpf, telefone, email) values (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, hospede.getNome());
            ps.setString(2, hospede.getCpf());
            ps.setString(3, hospede.getTelefone());
            ps.setString(4, hospede.getEmail());
            ps.execute();
            ps.close();
        } catch (NullPointerException |SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Hospede buscarHospedePorCpf(String cpf) {
        String sql = "SELECT id, nome, cpf, telefone, email from hospede where cpf = ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            Hospede h = null;  
            if(rs.next()) {
                h = new Hospede(); 
                h.setId(rs.getLong("id"));
                h.setNome(rs.getString("nome"));
                h.setCpf(rs.getString("cpf"));
                h.setTelefone(rs.getString("telefone"));
                h.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            return h;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
