package baseWithStates.doorstates;
// Defineix el paquet on està aquesta classe. Organitza les classes relacionades amb els estats de les portes.

public class Actions {
    // Classe que serveix com a contenidor de constants relacionades amb les accions possibles sobre una porta.
    // No té atributs ni mètodes de instància; només proporciona noms constants que representen accions.

    public static final String LOCK = "lock";
    // Constant per representar l'acció de tancar la porta amb clau o bloqueig.
    // 'public static final' significa que és accessible sense crear un objecte de la classe, i no es pot modificar.

    public static final String UNLOCK = "unlock";
    // Constant per representar l'acció de desbloquejar la porta.

    public static final String OPEN = "open";
    // Constant per obrir la porta.

    public static final String CLOSE = "close";
    // Constant per tancar la porta físicament (sense bloqueig).

}
