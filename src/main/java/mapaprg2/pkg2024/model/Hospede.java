package mapaprg2.pkg2024.model;

/**
 *
 * @author ricg_
 */
public class Hospede {
   private Long id;
   private String nome;
   private String cpf;
   private String telefone;
   private String email;
   
   public Long getId() {
       return this.id;
   }
   public void setId(Long X) {
       this.id = X;
   }
   
   public String getNome() {
       return this.nome;
   }
   
   public void setNome(String X) {
       this.nome = X;
   }
   
   public String getTelefone() {
       return this.telefone;
   }
   
   public void setTelefone(String X) {
       this.telefone = X;
   }
   
   public String getEmail() {
       return this.email;
   }
   
   public void setEmail(String X) {
       this.email = X;
   }
   
   public String getCpf() {
       return this.cpf;
   }
   
   public void setCpf(String X) {
       this.cpf = X;
   }
}