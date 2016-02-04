import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class AccesoDatos {
    static ObjectContainer db;
    
    public AccesoDatos(){
        db = Conexion.getDBConexion();
    }
    
    public synchronized Empleados procesarCadena(String str){
        int i;
        Empleados e = null;
        try{
            i = Integer.parseInt(str);
        }catch(NumberFormatException n){
            System.out.println("<<EMPLEADO: " + str + "INCORRECTO>>");
            return e;
        }
        Empleados emp = new Empleados(i, null);
        ObjectSet<Empleados> result = db.queryByExample(emp);
        if(result.size() == 0){
            System.out.println("<<EMPLEADO: " + i + " NO EXISTE>> ");
        }else{
            e = result.next();
        }
        return e;
    }
}