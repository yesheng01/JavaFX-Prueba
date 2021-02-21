package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

/**
 * sample
 * Nombre_project: JavaFX-Prueba
 * Pong_Sheng_Ye
 * Created by: sheng
 * Date : 16/02/2021
 * Description:
 **/
public class Pong_Sheng_Ye  extends Application {


    /** OBTENEMOS LOS VALORES DE LAS POSICIONES X - Y A TRAVES DE ESTA CLASE POSICION */

    public class Posicion {
        int posX;
        int posY;

        public Posicion(int x, int y) {
            this.posX = x;
            this.posY = y;
        }
    }

    /**  CREAMOS LA PRIMERA PALETA */

    class rectangle {

        /** CREAMOS SUS ATRIBUTOS DEFINIENDO SU POSICION , VELOCIDAD Y LO QUE VIENE SIENDO QUE SEA EN RECTANGULO */

        private Posicion posicion;
        private int vel = 50;
        private Pane panel;
        private Node rectangle;
        private int altura;


        /** INICIAMOS EL CONSTRUCTOR CON PARAMETRO AÑADIENDO LOS VALORES DEL RECTANGULO */
        public rectangle(Pane panel, int anchura, int altura) {
            this.posicion = new Posicion(anchura, altura);
            this.panel = panel;
            this.altura = altura;
            this.rectangle = new Rectangle(anchura, altura, Color.WHITE);
            final Bounds limits = panel.getBoundsInLocal();
            posicion.posX = (int) limits.getMinX() + 100;
            posicion.posY = (int) limits.getMaxY() / 2 - (60 / 2);
            rectangle.setLayoutX(posicion.posX);
            rectangle.setLayoutY(posicion.posY);
        }

        public Node getRectangle() {
            return rectangle;
        }

        /** METODO PARA QUE SE MUEVA PARA ARRIBA CUANDO LO MOVEMOS  DE LA PALETA*/

        public void mouAmunt() {
            posicion.posY = posicion.posY - this.vel;
            this.repinta();
            // System.out.println("Amunt pitjat");
        }

        /** METODO PARA QUE SE MUEVA PARA ABAJO CUANDO LO MOVEMOS  DE LA PALETA*/

        public void mouAbaix() {
            posicion.posY = posicion.posY + this.vel;
            this.repinta();
            // System.out.println("Abaix pitjat");
        }

        /** METODO PARA QUE NOS REPINTA LA PALETA DEPENDE DE LA POSICION*/

        private void repinta() {
            this.rectangle.setLayoutX(posicion.posX);
            this.rectangle.setLayoutY(posicion.posY);
        }
    }

    /**  CREAMOS LA SEGUNDA PALETA */

    class rectangle1 {
        private Posicion posicion;
        private int vel = 50;
        private Pane panel;
        private Node rectangle1;
        private int altura;

        /** INICIAMOS EL CONSTRUCTOR CON PARAMETRO AÑADIENDO LOS VALORES DEL RECTANGULO */
        public rectangle1(Pane pane, int anchura, int altura) {
            this.posicion = new Posicion(anchura, altura);
            this.panel = pane;
            this.altura = altura;
            this.rectangle1 = new Rectangle(anchura, altura, Color.WHITE);
            final Bounds limits = panel.getBoundsInLocal();
            posicion.posX = (int) limits.getMaxX() - 100;
            posicion.posY = (int) limits.getMaxY() / 2 - (60 / 2);
            rectangle1.setLayoutX(posicion.posX);
            rectangle1.setLayoutY(posicion.posY);
        }
        public Node getRectangle1() {
            return rectangle1;
        }

        /** METODO PARA QUE SE MUEVA PARA ARRIBA CUANDO LO MOVEMOS  DE LA PALETA*/

        public void mouAmunt() {
            posicion.posY = posicion.posY - this.vel;
            this.repinta();
            // System.out.println("Amunt pitjat");
        }

        /** METODO PARA QUE SE MUEVA PARA ABAJO CUANDO LO MOVEMOS  DE LA PALETA*/

        public void mouAbaix() {
            posicion.posY = posicion.posY + this.vel;
            this.repinta();
            // System.out.println("Abaix pitjat");
        }

        /** METODO PARA QUE NOS REPINTA LA PALETA DEPENDE DE LA POSICION*/

        private void repinta() {
            this.rectangle1.setLayoutX(posicion.posX);
            this.rectangle1.setLayoutY(posicion.posY);
        }
    }



    /** -------------------------------------------------------------------------------------------------------*/


    /** DEFINIMOS LOS OBJETOS AL QUE LE AÑADIREMOS DENTRO DEL JUEGO */

    /** VALOR ESTATICO DE LA ALTURA Y ANCHURA */
    public static final int altura = 600;
    public static final int anchura = 800;

    /** OBJETOS */
    public static Circle circle;
    public static Pane canvas;
    public static rectangle player1;
    public static rectangle1 player2;
    private Line line;
    private int jugador1 = 0;
    private int jugador2 = 0;

    /** TEXTO AL QUE APARECERAN DENTRO DEL JUEGO */
    public static Text start;
    private Text Game_over;
    public static Text Contador;
    public static Text Contador2;


    /** METODO START PARA EL EMPIEZO DEL JUEGO Y AÑADIREMOS SIGUIENTES COSAS*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        /** DEFINIMOS EL JUEGO */
        canvas = new Pane();
        final Scene escena = new Scene(canvas, anchura, altura, Color.BLACK);
        primaryStage.setTitle("Pong");
        primaryStage.setScene(escena);
        primaryStage.show();

        /** CREAMOS LAS DOS PALETAS , SU POSICION */

        player1 = new rectangle(canvas, 10, 80);
        player2 = new rectangle1(canvas, 10, 80);

        /** DEFINIMOS EL RADIO DE LA PELORA , SU COLOR Y LA POSICION EN QUE SE COLOCARA DESPUES
         *  DE QUE COMIENCE CADA VEZ */
        int radio = 10;
        circle = new Circle(radio, Color.WHITE);
        circle.relocate(400, 300);

        /** DEFINICMOS EL METODO PARA QUE NOS HAGA LA LINEA DEL MEDIO*/
        LineaEnMedio();

        /** DEFINIMOS EL METODO PARA QUE NOS MUESTRE LA LINEA DE CUANDO TOQUES CON SPACE */

        TocaEspacio();

        /** AÑADIMOS EL METODO CON LOS CONTADORES DE LOS JUGADORES */

        contadorJugador(player1);
        contadorJugador1(player2);


        /** DEFINIMOS LOS OBJETOS PARA LUEGO MOSTRARLO */
        canvas.getChildren().addAll(start);
        canvas.getChildren().addAll(circle);
        canvas.getChildren().addAll(player1.rectangle);
        canvas.getChildren().addAll(player2.rectangle1);
        canvas.getChildren().addAll(Contador , Contador2);

        /** DEFINIMOS EL PANEL COMO EL FOCUS */
        canvas.requestFocus();

        /** DEFINIMOS QUE CUANDO TOQUEMOS EL SPACE EMPIECE EL JUEGO*/
        canvas.setOnKeyPressed(e->{
            if (e.getCode().equals(KeyCode.SPACE)){
                Empiezaeljuego();
                canvas.getChildren().removeAll(start);
            }
        });

    }


    /** METODO QUE SIRVE PARA EJECUTAR EL PROGRAMA*/

    public void Empiezaeljuego() {

        /** IMPONEMOS LOS METODOS DE CUANDO TOQUE LAS TECLAS SUBA Y BAJE */

        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    player1.mouAmunt();
                    break;
                case S:
                    player1.mouAbaix();
                    break;
                case UP:
                    player2.mouAmunt();
                    break;
                case DOWN:
                    player2.mouAbaix();
                    break;
            }
        });


        /** SE CREA EL EVENTO*/

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            /** DEFINIMOS LOS REBOTES DE LA PELOTA AL TOCAR LAS PALETAS , LA VELOCIDAD EN ESTATICO Y TAMBIEN EL REBOTE */
            double angulo_en_radians = Math.toRadians(30);
            int velocidad = 3;
            int rebota = 0;
            double deltaX = velocidad * Math.cos(angulo_en_radians);
            double deltaY = velocidad * Math.sin(angulo_en_radians);

            /** DEFINIMOS LOS LIMITES*/

            final Bounds limits = canvas.getBoundsInLocal();

            /** METODO QUE SE UTILIZA PARA VER EL COMPORTAMIENTO DEL JUEGO*/

            @Override
            public void handle(ActionEvent actionEvent) {

                /**  DEFINIMOS UN BUCLE EN QUE CUANDO LOS JUGADORES LLEGUEN A SUS CONTADORES A 15 ENTONCES
                 * LE SALDRA EL TEXTO DE GAME OVER EN CADA UNO DE SUS SITIOS
                 * */

                if (jugador1 == 15 || jugador2 == 15) {
                    try {
                        if (jugador1 == 15) {
                            Game_over = new Text("Game Over");
                            Game_over.setFont(new Font(50));
                            Game_over.relocate(450, 100);
                            Game_over.setFill(Color.BLUE);
                            canvas.getChildren().add(Game_over);
                        }else if (jugador2 == 15) {
                            Game_over = new Text("Game Over");
                            Game_over.setFont(new Font(50));
                            Game_over.relocate(100, 100);
                            Game_over.setFill(Color.BLUE);
                            canvas.getChildren().add(Game_over);
                        }
                    } catch (Exception e) {
                        return;
                    }
                } else {
                    /** PONEMOS LOS CICLOS QUE SE HARA LA PELOTA*/

                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);

                    /** DEFINIMOS LOS LIMITES DE LA PELOTA Y DE LAS PALETAS */

                    final boolean alLimitDret = circle.getLayoutX() >= (limits.getMaxX() - circle.getRadius());
                    final boolean alLimitEsquerra = circle.getLayoutX() <= (limits.getMinX() + circle.getRadius());
                    final boolean alLimitInferior = circle.getLayoutY() >= (limits.getMaxY() - circle.getRadius());
                    final boolean alLimitSuperior = circle.getLayoutY() <= (limits.getMinY() + circle.getRadius());

                    final boolean alLimitInferiorplayer = player1.rectangle.getLayoutY() > (limits.getMaxY() - player1.altura);
                    final boolean alLimitInferiorplayer2 = player2.rectangle1.getLayoutY() > (limits.getMaxY() - player2.altura);
                    final boolean alLimitSuperiorplayer = player1.rectangle.getLayoutY() <= (limits.getMinY() -28);
                    final boolean alLimitSuperiorplayer2 = player2.rectangle1.getLayoutY() <= (limits.getMinY() -28);


                    /**AÑADIMOS UN BUCLE EN QUE SI LA PELOTA TOCA 5 VECES A LAS PALETAS ENTONCES AUMENTA LA VELOCIDAD*/

                    if (circle.getBoundsInParent().intersects(player1.getRectangle().getBoundsInParent()) ||circle.getBoundsInParent().intersects(player2.getRectangle1().getBoundsInParent())) {
                        if (rebota == 5) {
                            deltaX += 1 * Math.signum(deltaX);
                            rebota = 0;
                        }else {
                            rebota++;
                            System.out.println(rebota);
                            System.out.println(deltaX);
                        }
                        deltaX *= -1;
                    }

                    /** SI LA PELOTA TOCA DEPENDE DE CADA SITIO DERECHO O IZQUIERDO , SE AÑADE UN PUNTO AL JUGADOR QUE DIGAMOS
                     * QUE ES MARCADO Y TAMBIEN LE PONDRA LA PELOTA EN LA POSICION QUE TOCA AL REINICIAR  Y LA VELOCIDAD SE
                     * RESETEA UNA VEZ QUE ES TOCADO AL PARED*/

                    if (alLimitDret || alLimitEsquerra) {
                        if (alLimitDret) {
                            deltaX = velocidad * Math.cos(angulo_en_radians);
                            deltaX *=-1;
                            circle.relocate(400, 300);
                            jugador1++;
                            Contador.setText(jugador1 + "");
                        } else if (alLimitEsquerra) {
                            deltaX = velocidad * Math.cos(angulo_en_radians);
                            deltaY *=-1;
                            circle.relocate(400, 300);
                            jugador2++;
                            Contador2.setText(jugador2 + "");
                        }
                        deltaX *=-1;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /**  PONEMOS QUE SI HAY 5 REBOTES ENTONCES SE LE IGUALA AL 0 CADA VEZ QUE SE RESETEE*/
                        if (rebota > 5) {
                            rebota = 0;
                        }
                        circle.setLayoutX(anchura/2);
                        circle.setLayoutY((Math.random()*(altura-circle.getRadius()))+circle.getRadius());
                    }

                    /** DEFINIMOS LOS LIMITES SUPERIOR Y INFERIOR */

                    if (alLimitInferior || alLimitSuperior) {
                        deltaY *= -1;

                    }

                    /** AL TOCAR EL SITIO QUE TOCA ENTONCES SE DA AL LADO CONTRARIO*/

                    if (alLimitSuperiorplayer) {
                        player1.mouAbaix();
                    }
                    if (alLimitSuperiorplayer2) {
                        player2.mouAbaix();
                    }
                    if (alLimitInferiorplayer) {
                        player1.mouAmunt();
                    }
                    if (alLimitInferiorplayer2) {
                        player2.mouAmunt();
                    }
                }

            }
        }));

        /**  CREAMOS EL LOOP */

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    /** METODO DE QUE TOQUES EL ESPACIO */

    public void TocaEspacio(){
        Font font = new Font("ARIAL" , 25);
        start =new Text("Dale al espacio para iniciar el juego");
        start.setFont(font);
        start.setFill(Color.BLUE);
        start.setLayoutX(anchura/2);
        start.setLayoutY(altura/4);
    }

    /** METODO QUE TE GENERA LA LINEA DEL MEDIO*/

    public void LineaEnMedio(){
        for (int i = 0; i < altura; i+=30) {
            line = new Line(anchura / 2, i, anchura / 2, i+10);
            line.setStrokeWidth(4);
            line.setStroke(Color.WHITE);
            canvas.getChildren().addAll(line);
        }
    }


    /** METODO DE LOS CONTADORES DE LOS JUGADORES*/

    public void contadorJugador(rectangle player){
        if (player.equals(player1)){
            Font font = new Font("ARIAL" , 25);
            Contador=new Text("0");
            Contador.setFont(font);
            Contador.setFill(Color.WHITE);
            Contador.setLayoutX(anchura/4);
            Contador.setLayoutY(altura/7);
        }else {
            return;
        }
    }

    public void contadorJugador1(rectangle1 player) {
        if (player.equals(player2)) {
            Font font = new Font("ARIAL", 25);
            Contador2 = new Text("0");
            Contador2.setFont(font);
            Contador2.setFill(Color.WHITE);
            Contador2.setLayoutX(anchura - 200);
            Contador2.setLayoutY(altura / 7);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
