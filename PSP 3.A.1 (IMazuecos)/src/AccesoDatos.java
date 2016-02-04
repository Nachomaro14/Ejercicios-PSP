import com.db4o.ObjectContainer;

public class AccesoDatos {
    static ObjectContainer db;
    
    public AccesoDatos(){
        db = Conexion.getDBConexion();
    }
    
    public void insertar(int nu, String no, String lo){
        //Aquí se insertaría el objecto Departamentos con sus respectivos valores
    }
    
    public synchronized String insertarDepartamento(Departamentos nd){
        String respuesta = "";
        int nu = nd.getDeptNo();
        String no = nd.getDnombre();
        String lo = nd.getLoc();
        try{
            insertar(nu, no, lo);
            respuesta = "Departamento insertado...";
        }catch(Exception e){
            e.printStackTrace();
            respuesta = "Error al insertar Departamento";
        }
        return respuesta;
    }
}