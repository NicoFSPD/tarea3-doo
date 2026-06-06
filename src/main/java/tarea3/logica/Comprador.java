package org.example;
/**Clase de la maquina expendedora
 * @author Eduardo Riveros
 * @author Daniel Lopez
 * @author Nicolas Silva
 * @version version 1, 24 de Abril de 2026
 */
public class Expendedor{
    /*Contenedores para cada producto y para los vueltos*/
        private Deposito<Producto> coca;
        private Deposito<Producto> sprite;
        private Deposito<Producto> fanta;
        private Deposito<Producto> snickers;
        private Deposito<Producto> super8;
        private Deposito<Moneda> monVu;
        private Producto productoEnDepositoRetiro = null;  //ATRIBUTO NUEVO TAREA 3
        private Deposito<Moneda> monedasAlmacenadas;    //ATRIBUTO NUEVO TAREA 3

    /**Constructor para inicializar los contenedores
     * @param numProductos cantidad de productos pquiero que modifior contenedor
     * */
    public Expendedor(int numProductos){

        this.coca = new Deposito<>();
        this.sprite = new Deposito<>();
        this.fanta = new Deposito<>();
        this.snickers = new Deposito<>();
        this.super8 = new Deposito<>();
        this.monVu = new Deposito<>();

        for (int i=0;i<numProductos;i++){
            coca.addElemento(new CocaCola(100+i));
            sprite.addElemento(new Sprite(200+i));
            fanta.addElemento(new Fanta(300+i));
            snickers.addElemento(new Snickers(400+i));
            super8.addElemento(new Super8(500+i));
        }

        this.monedasAlmacenadas = new Deposito<Moneda>();

    }
    /**Comprar un producto con una moneda y un numero de seleccion de producto
     * @param m La moneda a utilizar
     * @param cual El selector
     * @return El producto de haber sido una compra exitosa, nada en caso contrario
     * @throws PagoIncorrectoException si se quiere comprar un producto sin una moneda (null)
     * @throws PagoInsuficienteException si el producto que se busca comprar tiene valor mayor al de la moneda ingresada
     * */
    public void comprarProducto(Moneda m, int cual) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException, DepositoObstruidoException {
        if (m == null){
            throw new PagoIncorrectoException("No se ha insertado ninguna moneda\n");
        }

        if (this.productoEnDepositoRetiro != null){
            monVu.addElemento(m);
            throw new DepositoObstruidoException("Deposito de salida ocupado. Retire el producto anterior");
        }

        int precio;
        Deposito<? extends Producto> D = null;

        if(cual == Opcion.COCA.ID) {precio = Opcion.COCA.precio; D = coca;}
        else if(cual == Opcion.SPRITE.ID) {precio = Opcion.SPRITE.precio; D = sprite;}
        else if(cual == Opcion.FANTA.ID) {precio = Opcion.FANTA.precio; D = fanta;}
        else if(cual == Opcion.SNICKERS.ID) {precio = Opcion.SNICKERS.precio; D = snickers;}
        else if(cual == Opcion.SUPER8.ID) {precio = Opcion.SUPER8.precio; D = super8;}
        else{
            monVu.addElemento(m);
            throw new NoHayProductoException("el producto no existe dentro del expendedor");
        }

        if (m.getValor() >= precio){
            Producto productoExtraido = null;


            try {
                productoExtraido =D.getElemento();
            } catch (NoHayProductoException e){
                monVu.addElemento(m);
                throw e;
            }
            this.productoEnDepositoRetiro=productoExtraido;  //guardar el producto para retirar
            this.monedasAlmacenadas.addElemento(m); //guardar la moneda de pago en el baul

            int vueltoTotal = m.getValor()-precio;    //calculo de vuelto a entregar

            while (vueltoTotal>=500){        //entrega la mayor cantidad de monedas de 500 que se pueda
                monVu.addElemento(new Moneda500());
                vueltoTotal -= 500;
            }

            while (vueltoTotal>0){   //el resto de lo anterior se entrega de a 100
                monVu.addElemento(new Moneda100());
                vueltoTotal -=100;
            }

        }
        else{
            monVu.addElemento(m);
            throw new PagoInsuficienteException("El producto que quieres comprar tiene un valor mayor a lo que estás pagando");
        }
    }
    /**Retirar el vuelto en caso de haber.
     * @throws NoHayProductoException si es que el deposito de vuelto esta vacio
     * @return Las monedas de vuelto que pueda haber
     * */
    public Moneda getVuelto() throws NoHayProductoException {
        return monVu.getElemento();
    }

    //NUEVO METODO TAREA 3, aún no terminado..

    public Producto getProducto(){
        Producto p = this.productoEnDepositoRetiro;
        this.productoEnDepositoRetiro = null;   //al retirar lo vacia.
        return p;
    }

    //puse los limites basandome en la foto que mando el dani lopez, este es el metodo
    //que rellena magicamente los depositos
    public void rellenarDepositos(){
        while (coca.getCantidad()<9){
            coca.addElemento(new CocaCola(100 + coca.getCantidad()));
        }
        while (sprite.getCantidad()<9){
            sprite.addElemento(new Sprite(200 + sprite.getCantidad()));
        }
        while (fanta.getCantidad()<9){
            fanta.addElemento(new Fanta(300 + fanta.getCantidad()));
        }
        while (snickers.getCantidad()<4){
            snickers.addElemento(new Snickers(400 + snickers.getCantidad()));
        }
        while (super8.getCantidad()<4){
            super8.addElemento(new Super8(500 + super8.getCantidad()));
        }
    }



    //getters y metodo necesario para frontend de mi parte (edu)
    public Deposito<Producto> getDepositoCoca(){
        return this.coca;
    }
    public Deposito<Producto> getDepositoSprite(){
        return this.sprite;
    }
    public Deposito<Producto> getDepositoFanta(){
        return this.fanta;
    }
    public Deposito<Producto> getDepositoSnickers(){
        return this.snickers;
    }
    public Deposito<Producto> getDepositoSuper8(){
        return this.super8;
    }

    public Producto peekProductoRetiro() {
        return this.productoEnDepositoRetiro;
    }



}