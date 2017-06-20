package com.ueno;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/world?useSSL=false";
        String username = "root";
        String password = "admin";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            Statement stmt = connection.createStatement();
            connection.setAutoCommit(false);
            String sql;
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1', '22222222233', 'Cristiano', 'A', '1587')";
            stmt.addBatch(sql);
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1500', '123456789', 'João', 'A', '2000')";
            stmt.addBatch(sql);
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1501', '000000000', 'Maria', 'I', '50')";
            stmt.addBatch(sql);
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1502', '111111111', 'Mario', 'A', '1000')";
            stmt.addBatch(sql);
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1565', '222222222', 'Ricardo', 'A', '2600')";
            stmt.addBatch(sql);
            sql = "INSERT INTO tb_customer_account (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) " +
                    "VALUES ('1700', '12345698774', 'Leticia', 'A', '500')";
            stmt.addBatch(sql);
            int[] count = stmt.executeBatch();
            connection.commit();

            sql = "SELECT * FROM tb_customer_account WHERE (id_customer BETWEEN 1500 AND 2700)" +
                    " AND vl_total > 560";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //Recupera valor referente ao nome da coluna
                int idCustomer = rs.getInt("id_customer");
                //Recupera o índice do campo referente ao campo nome
                String cpfCnpj = rs.getString("cpf_cnpj");
                String nmCustomer = rs.getString("nm_customer");
                //String nomeCompleto = nome.concat(" "+sobreNome);
                String isActive = rs.getString("is_active");
                String ativo;
                if (isActive.equals("A")){
                    ativo = "Sim";
                } else {
                    ativo = "Não";
                }
                int vlTotal = rs.getInt("vl_total");
                System.out.printf("ID %d | Nome: %s - CPF/CNPJ: %s | Ativo: %s | Salário: %d \n",idCustomer,nmCustomer,
                        cpfCnpj, ativo, vlTotal);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }



    }
}
