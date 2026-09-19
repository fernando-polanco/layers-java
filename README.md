# Arquitecturas de Software

## Arquitectura de capas

### Integrantes

- Basto Chavarría Joel Humberto
- Basulto Camara José Luis
- Osorno Herrera José Alberto
- Polanco Casares Fernando

### Propósito

Implementar en Java un prototipo del sistema de procesamiento de pedidos utilizando el estilo arquitectónico Capas, separando claramente las responsabilidades de presentación, lógica de negocio, acceso a datos y almacenamiento.

### Requisitos funcionales

La empresa necesita una aplicación sencilla para registrar y consultar pedidos. Cada pedido pertenece a un cliente y contiene uno o más productos. Al registrar un pedido, el sistema deberá validar los datos, calcular subtotal, descuento, impuestos y total, almacenar el pedido y proporcionar al usuario una confirmación. Para facilitar futuros cambios, la aplicación deberá organizarse mediante una arquitectura en capas.

**Estructura arquitectónica requerida**

La solución deberá contener, como mínimo:

- Presentación
- Lógica de negocio
- Acceso a datos
- Datos

### Layers

#### Presentación

Responsable de:

- Capturar datos;
- Solicitar operaciones a la capa de negocio;
- Mostrar resultados y errores.
- Puede implementarse inicialmente mediante una aplicación de consola.

**Ejemplos:**

```
MenuPrincipal
PedidoUI
```

No deberá:

- Calcular totales;
- Aplicar descuentos;
- Consultar directamente estructuras de almacenamiento;
- Ejecutar SQL.

#### Lógica de negocio

Responsable de:

- Validar pedidos;
- Calcular subtotal;
- Aplicar descuentos;
- Calcular impuestos;
- Determinar el estado del pedido;
- Coordinar el registro y consulta de pedidos.

Una posible clase:

```java
public class PedidoService {

    private PedidoRepository repositorio;

    public PedidoService(PedidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Pedido registrar(Pedido pedido) {
        // reglas de negocio
        return pedido;
    }
}
```

Esta capa no deberá conocer cómo se almacenan físicamente los pedidos.

#### Acceso a datos

Se deberá definir un contrato para la persistencia:

```java
public interface PedidoRepository {
    int guardar(Pedido pedido);
    Pedido buscarPorId(int id);
}
```

Y una primera implementación:

```java
public class PedidoRepositoryMemoria
    implements PedidoRepository {
    // almacenamiento en memoria
}
```

La lógica de negocio deberá depender de PedidoRepository, no directamente de PedidoRepositoryMemoria.

#### Datos

Para la primera versión se utilizará una colección Java:

```java
Map<Integer, Pedido> pedidos = new HashMap<>();
```

o:

```java
List<Pedido> pedidos = new ArrayList<>();
```

No es necesario utilizar una base de datos real.

### Modelo mínimo

El proyecto deberá incluir como mínimo:

- Pedido
- Producto

Un Producto deberá contener:

- nombre
- precio
- cantidad
- existencia

Un Pedido deberá contener:

- id
- cliente
- productos
- subtotal
- descuento
- impuestos
- total
- estado
- reglas de negocio

El sistema deberá aplicar las siguientes reglas:

**Validación**

- El cliente no puede estar vacío.
- El pedido debe contener al menos un producto.
- La cantidad solicitada debe ser mayor que cero.
- No puede solicitarse una cantidad superior a la existencia disponible. 

**Subtotal**

`subtotal = Σ precio × cantidad`

**Descuento**

Sí `subtotal >= $1,000` se aplicará 10 % de descuento. En otro caso `descuento = 0`.

**Impuestos**

`16 % sobre subtotal - descuento`

**Total**

`total = subtotal - descuento + impuestos`

Después de registrarse correctamente:

`estado = "PROCESADO"`

### Funcionalidades requeridas

El menú deberá permitir al menos:

1. Registrar pedido
2. Consultar pedido por ID
3. Listar pedidos
4. Salir

Para registrar un pedido pueden utilizar productos creados directamente en el código para evitar que la actividad se convierta en un ejercicio complejo de captura.

**Primera parte: implementar las capas**

Una organización posible del proyecto sería:

```
src
│
├── presentacion
│ └── PedidoUI.java
│
├── negocio
│ └── PedidoService.java
│
├── datos
│ ├── PedidoRepository.java
│ └── PedidoRepositoryMemoria.java
│
└── modelo
├── Pedido.java
└── Producto.java
```

No es obligatorio utilizar exactamente estos paquetes, pero la separación de responsabilidades deberá ser evidente.

**Segunda parte: comprobar la arquitectura**

Una vez funcionando la aplicación, deberán incorporar una segunda implementación del repositorio:

```java
public class PedidoRepositoryArchivo implements PedidoRepository {
}
```

Puede utilizar un archivo de texto sencillo. No es necesario implementar JSON ni utilizar bibliotecas externas.

Entonces deberán sustituir:

`PedidoRepositoryMemoria`

por:

`PedidoRepositoryArchivo`

**Restricción fundamental**

Para realizar este cambio no deberán modificar:

- PedidoService
- PedidoUI
- Pedido
- Producto

Idealmente, el cambio deberá realizarse únicamente al construir la aplicación:

```java
PedidoRepository repositorio = new PedidoRepositoryArchivo();
PedidoService servicio = new PedidoService(repositorio);
```

La solución deberá cumplir obligatoriamente:

- Presentación únicamente utiliza servicios de negocio.
- Negocio no contiene código de interfaz.
- Negocio no conoce la estructura utilizada para almacenar los pedidos.
- Acceso a datos no contiene reglas de negocio.
- La persistencia se accede mediante un contrato.
- Las dependencias apuntan hacia capas inferiores.
- No se permite que la presentación acceda directamente al repositorio.
- Cambiar la implementación del repositorio no deberá modificar la lógica de negocio.
- Evidencias de ejecución

Deberán demostrar al menos:

- Pedido válido
- Pedido sin descuento
- Producto sin existencia suficiente
- Consulta
- Sustitución del repositorio

**Entregables**

- Proyecto Java completo.
- Código fuente.
- Diagrama UML de la arquitectura implementada.
- Evidencias de ejecución de los cinco casos.

Entrega por equipo.

Formato del archivo: Apellido1Apellido2Apellido3Apellido4.ZIP.

Fecha límite de entrega: 29 de septiembre.