Context envContext = null;        
try {
	envContext = new InitialContext();
	Context initConntext = (Context)envContext.lookup("java:/comp/env");
	DataSource ds = (DataSource) initConntext.lookup("jdbc/FatecKombat");
	Connection con = ds.getConnection();
	
	//Statement stmt = con.createStatement(); --> Recomendado para Operações de Atualização: INSERT, UPDATE, DELETE;
	//PreparedStatement ps = con.prepareStatement(query); --> Recomendado para Consultas;
} catch (NamingException ex) {
		Logger.getLogger(ServletConsulta.class.getName()).log(Level.SEVERE, null, ex);
} catch (SQLException ex) {
		Logger.getLogger(ServletConsulta.class.getName()).log(Level.SEVERE, null, ex);
}   