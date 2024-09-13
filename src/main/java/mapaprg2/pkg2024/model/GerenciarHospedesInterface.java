package mapaprg2.pkg2024.model;
import javax.swing.JOptionPane;
import mapaprg2.pkg2024.dao.HospedeDAO;

public interface GerenciarHospedesInterface { 
    default String SearchForCpf(String param) {
        String msg;
        if(param.length() == 14) {
           Hospede h = new HospedeDAO().buscarHospedePorCpf(param);
           msg = "Hospede não encontrado";
           if(h != null) {
               msg = "Id: "+h.getId()+"\nNome: "+h.getNome()+"\nCPF: "+h.getCpf()+"\nTelefone: "+h.getTelefone()+"\nEmail: "+h.getEmail();
           } 
        } else if(param == null || "".equals(param)) {
            msg = "O campo não pode estar vazio";
        } else {
            msg = "O CPF digitado é inválido";
        }
        return msg;
    }
    
    default boolean Save(String Nome, String CPF, String Telefone, String Email) {
        if (Nome.length() >= 3 && Email.length() >= 10 && CPF.length() == 14 && Telefone.length() >= 8) {
            Hospede h = new Hospede();
            h.setNome(Nome);
            h.setCpf(CPF);
            h.setTelefone(Telefone);
            h.setEmail(Email);
            try {
                new HospedeDAO().cadastraHospede(h);
                return true;
            } catch (ClassNotFoundException e) {
               JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos corretamente", "Erro", JOptionPane.ERROR_MESSAGE);
        }
       
        return false;
    }
 
    void limparCampos();
    
    default void Clear(javax.swing.JTextField param) {
        param.setText("");
    }
}
