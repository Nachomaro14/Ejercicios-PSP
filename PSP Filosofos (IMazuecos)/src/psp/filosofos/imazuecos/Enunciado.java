package psp.filosofos.imazuecos;

public class Enunciado {

    //La cena de los filósofos es un problema típico en programación de sincronización de procesos.
    //Enunciado:
    //Cinco filósofos alrededor de una mesa pasan su vida comiendo o pensando. Cada filósofo tiene
    //un plato de arroz y un palillo a la izquierda de su plato. Cuando un filósofo quiere comer,
    //cogerá los dos palillos de cada lado del plato y comerá. 
    //El problema es desarrollar el algoritmo que permita comer a los filósofos.
    //Este algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden
    //emplear el mismo palillo a la vez), además de evitar el interbloqueo y la inanición.
    
    //La combinación de filósofos comiendo sería AC, AD, BD, BE, CE.
    //Y la combinación de filósofs pensando sería BDE, BCE, ACE, ACD, ABD.
    
    //La idea sería que cada vez que un filósofo trate de comer compruebe si cualquiera de los filósofos
    //que deberían estar pensando (según la combinación) está comiendo.
    
    //Enfocaré el ejercicio de manera que los palillos representen a los hilos y se imprima por pantalla
    //que un filósofo está comiendo cuando sus dos hipotéticos palillos contiguos estén en uso.
}