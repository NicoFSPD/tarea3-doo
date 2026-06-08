package tarea3.logica;

/**
 * Clase que representa el modelo logico de la maquina expendedora.
 * Se encarga de gestionar el almacenamiento de productos, el procesamiento de pagos,
 * la distribucion de vueltos en multiples denominaciones y el rellenado de stock.
 * @author Eduardo Riveros
 * @author Daniel Lopez
 * @author Nicolas Silva
 * @version version 1.2, 6 de junio de 2026
 */
public class Expendedor {
    private Deposito<Producto> coca;
    private Deposito<Producto> sprite;
    private Deposito<Producto> fanta;
    private Deposito<Producto> snickers;
    private Deposito<Producto> super8;
    private Deposito<Moneda> monVu;
    private Producto productoEnDepositoRetiro = null;
    private Deposito<Moneda> monedasAlmacenadas;
    private int capacidadMaximaPorDeposito;
    private Deposito<Moneda> monedasEnEspera = new Deposito<>();

    /**
     * Constructor para inicializar los contenedores y abastecer la maquina.
     * @param numProductos cantidad inicial y capacidad maxima de productos por contenedor.
     */
    public Expendedor(int numProductos) {
        this.capacidadMaximaPorDeposito = numProductos;

        this.coca = new Deposito<>();
        this.sprite = new Deposito<>();
        this.fanta = new Deposito<>();
        this.snickers = new Deposito<>();
        this.super8 = new Deposito<>();
        this.monVu = new Deposito<>();
        this.monedasAlmacenadas = new Deposito<>();

        /**
         * Abastecimiento inicial automatizado
         */
        for (int i = 0; i < numProductos; i++) {
            coca.addElemento(new CocaCola(100 + i));
            sprite.addElemento(new Sprite(200 + i));
            fanta.addElemento(new Fanta(300 + i));
            snickers.addElemento(new Snickers(400 + i));
            super8.addElemento(new Super8(500 + i));
        }
    }

    /**
     * Procesa la compra de un producto utilizando una moneda de pago.
     * Si la transaccion es exitosa, el producto se desplaza al deposito de retiro de capacidad uno
     * y el vuelto se desglosa en monedas de $1500, $1000, $500 y $100.
     * @param cual La ID del producto seleccionado.
     * @throws PagoIncorrectoException si no se han ingresado monedas.
     * @throws DepositoObstruidoException si el compartimento de retiro ya contiene un producto.
     * @throws NoHayProductoException si el producto seleccionado no existe o esta agotado.
     * @throws PagoInsuficienteException si el saldo acumulado es menor al precio del producto.
     */
    public void comprarProducto(int cual) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException, DepositoObstruidoException {
        int saldoAcumulado = 0;
        int cantMonedasEspera = monedasEnEspera.getCantidad();

        for (int i = 0; i < cantMonedasEspera; i++) {
            saldoAcumulado += monedasEnEspera.getElementoPorIndice(i).getValor();
        }

        if (saldoAcumulado == 0) {
            throw new PagoIncorrectoException("No se ha insertado ninguna moneda.");
        }

        if (this.productoEnDepositoRetiro != null) {
            vaciarDepositoHacia(monedasEnEspera, monVu);
            throw new DepositoObstruidoException("Deposito de salida ocupado. Retire el producto anterior.");
        }

        int precio;
        Deposito<? extends Producto> D = null;

        if (cual == Opcion.COCA.ID) { precio = Opcion.COCA.precio; D = coca; }
        else if (cual == Opcion.SPRITE.ID) { precio = Opcion.SPRITE.precio; D = sprite; }
        else if (cual == Opcion.FANTA.ID) { precio = Opcion.FANTA.precio; D = fanta; }
        else if (cual == Opcion.SNICKERS.ID) { precio = Opcion.SNICKERS.precio; D = snickers; }
        else if (cual == Opcion.SUPER8.ID) { precio = Opcion.SUPER8.precio; D = super8; }
        else {
            vaciarDepositoHacia(monedasEnEspera, monVu);
            throw new NoHayProductoException("El producto no existe dentro del expendedor.");
        }

        if (saldoAcumulado >= precio) {
            Producto productoExtraido = null;
            try {
                productoExtraido = D.getElemento();
            } catch (NoHayProductoException e) {
                vaciarDepositoHacia(monedasEnEspera, monVu);
                throw e;
            }

            this.productoEnDepositoRetiro = productoExtraido;
            vaciarDepositoHacia(monedasEnEspera, monedasAlmacenadas);

            int vueltoTotal = saldoAcumulado - precio;

            while (vueltoTotal >= 1500) { monVu.addElemento(new Moneda1500()); vueltoTotal -= 1500; }
            while (vueltoTotal >= 1000) { monVu.addElemento(new Moneda1000()); vueltoTotal -= 1000; }
            while (vueltoTotal >= 500)  { monVu.addElemento(new Moneda500());  vueltoTotal -= 500;  }
            while (vueltoTotal >= 100)  { monVu.addElemento(new Moneda100());  vueltoTotal -= 100;  }

        } else {
            vaciarDepositoHacia(monedasEnEspera, monVu);
            throw new PagoInsuficienteException("Saldo insuficiente ($" + saldoAcumulado + "). Se necesitan $" + precio);
        }
    }

    /**
     * Transfiere de forma secuencial todos los elementos de un deposito a otro.
     */
    private void vaciarDepositoHacia(Deposito<Moneda> origen, Deposito<Moneda> destino) {
        while (origen.getCantidad() > 0) {
            try {
                destino.addElemento(origen.getElemento());
            } catch (NoHayProductoException e) {
                break;
            }
        }
    }

    public void recibirMonedaEnEspera(Moneda m) { this.monedasEnEspera.addElemento(m); }

    public int getSaldoEnEspera() {
        int total = 0;
        for (int i = 0; i < monedasEnEspera.getCantidad(); i++) {
            total += monedasEnEspera.getElementoPorIndice(i).getValor();
        }
        return total;
    }

    /**
     * Extrae de forma secuencial las monedas almacenadas en el deposito de vuelto.
     * @return La siguiente moneda de vuelto disponible.
     * @throws NoHayProductoException si el deposito de vuelto se encuentra vacio.
     */
    public Moneda getVuelto() throws NoHayProductoException {
        return monVu.getElemento();
    }

    /**
     * Permite al comprador retirar el producto del deposito especial de salida, vaciandolo.
     * @return El producto almacenado en el compartimento de retiro.
     */
    public Producto getProducto() {
        Producto p = this.productoEnDepositoRetiro;
        this.productoEnDepositoRetiro = null;
        return p;
    }

    /**
    * Rellena de forma automatica todos los depositos de productos que se encuentren por debajo
    * de la capacidad maxima definida en el constructor.
    */
    public void rellenarDepositos() {
        while (coca.getCantidad() < capacidadMaximaPorDeposito) {
            coca.addElemento(new CocaCola(100 + coca.getCantidad()));
        }
        while (sprite.getCantidad() < capacidadMaximaPorDeposito) {
            sprite.addElemento(new Sprite(200 + sprite.getCantidad()));
        }
        while (fanta.getCantidad() < capacidadMaximaPorDeposito) {
            fanta.addElemento(new Fanta(300 + fanta.getCantidad()));
        }
        while (snickers.getCantidad() < capacidadMaximaPorDeposito) {
            snickers.addElemento(new Snickers(400 + snickers.getCantidad()));
        }
        while (super8.getCantidad() < capacidadMaximaPorDeposito) {
            super8.addElemento(new Super8(500 + super8.getCantidad()));
        }
    }

    /**
    * Getters para el gui
    */

    public Deposito<Producto> getDepositoCoca() { return this.coca; }
    public Deposito<Producto> getDepositoSprite() { return this.sprite; }
    public Deposito<Producto> getDepositoFanta() { return this.fanta; }
    public Deposito<Producto> getDepositoSnickers() { return this.snickers; }
    public Deposito<Producto> getDepositoSuper8() { return this.super8; }
    public int getCantidadMonedasAlmacenadas() {
        return this.monedasAlmacenadas.getCantidad();
}
    public Moneda getMonedaAlmacenadaPorIndice(int i) {
        return this.monedasAlmacenadas.getElementoPorIndice(i);
}
    /**
    * Permite a la interfaz grafica espiar el compartimento de retiro
    * para saber que producto dibujar sin removerlo del deposito.
    * @return El producto en espera, o null si esta vacio.
    */
    public Producto peekProductoRetiro() {
           return this.productoEnDepositoRetiro;
    }
}
