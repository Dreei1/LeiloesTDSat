
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
  
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
          prep = this.conn.prepareStatement(sql);
          prep.setString(1, produto.getNome());
          prep.setInt(2, produto.getValor());
          prep.setString(3, produto.getStatus());
          prep.execute();
          JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        }
        catch(Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
    
    public List<ProdutosDTO> listarProduto(String id){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
          
            resultset = prep.executeQuery();
              
            List<ProdutosDTO> listagem = new ArrayList<>();
        
              while (resultset.next()) {
                  ProdutosDTO produtosDto = new ProdutosDTO();

                  produtosDto.setId(resultset.getInt("id"));
                  produtosDto.setNome(resultset.getString("nome"));
                  produtosDto.setValor(resultset.getInt("valor"));
                  produtosDto.setStatus(resultset.getString("status"));
                  
                  listagem.add(produtosDto);
              }
        return listagem;
        }
        catch(Exception e) {
                return null;
                }
    }
    
    public void venderProduto(int id) {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        String status = "Vendido";
        try {
            prep = conn.prepareStatement(sql);
             prep.setString(1, status);
             prep.setInt(2, id);
             prep.execute();
            
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro.");
        }
    }
    
    
    
    
        
}

