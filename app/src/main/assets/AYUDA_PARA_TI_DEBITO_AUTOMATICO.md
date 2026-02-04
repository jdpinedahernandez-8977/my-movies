# Especificación Técnica: Débitos Automáticos - Banco Itaú Uruguay

Este archivo `.md` ha sido diseñado por un especialista en la estructuración de datos para modelos de inteligencia artificial, proporcionando una descripción exhaustiva, técnica y procedimental sobre el servicio de **Débitos Automáticos** de Banco Itaú Uruguay, basada exclusivamente en las fuentes proporcionadas.

## 1. Definición del Servicio
El débito automático es un servicio financiero orientado a la automatización de pagos recurrentes en una fecha específica determinada por el ente o comercio. Su función principal es sustituir la gestión manual de pagos, permitiendo que el banco ejecute la transacción de forma autónoma siempre que existan fondos suficientes en la cuenta seleccionada por el cliente en la fecha de vencimiento.

## 2. Procedimientos de Adhesión (Alta del Servicio)
La modalidad de suscripción varía significativamente según el instrumento de pago y el sello de la tarjeta:

*   **Tarjetas de Crédito Visa:** El cliente debe gestionar la adhesión comunicándose directamente con el comercio prestador del servicio o ingresando al portal de Totalnet (`www.pagos.totalnet.uy`).
*   **Entes Públicos (con Visa):** La gestión de facturas de organismos estatales debe realizarse exclusivamente a través de la comunicación directa con Totalnet.
*   **Tarjetas de Crédito Mastercard:** La solicitud de alta debe tramitarse de manera directa con el comercio o el ente público correspondiente, sin intermediación digital de portales de terceros mencionados para Visa.

## 3. Plazos y Efectividad Operativa
La activación real del débito no es inmediata tras la solicitud, ya que está sujeta a ciclos de procesamiento interbancarios y administrativos:
*   **Transmisión de datos:** El banco consolida y envía la información de nuevas adhesiones a los entes y comercios todos los jueves de cada semana.
*   **Procesamiento externo:** La fecha efectiva del primer débito depende de los cronogramas internos de cada entidad facturadora.
*   **Recomendación de seguridad:** Se aconseja al cliente verificar directamente con el comercio si el primer pago tras la solicitud ya se procesará por la vía automática o si aún debe realizarse de forma manual.

## 4. Gestión de Bajas (Cancelación del Servicio)
El cese de la automatización de pagos requiere acciones específicas según el producto:
*   **Gestión General vía Web:** El cliente debe autenticarse en `itau.com.uy` con su documento y contraseña, navegar al menú **"Pagos"** y seleccionar **"Baja de débito automático"** para completar el formulario requerido.
*   **Restricción en Tarjetas de Crédito:** Para cancelar débitos asociados a tarjetas de crédito, la baja debe gestionarse adicionalmente ante el comercio adherido o directamente con el sello de la tarjeta (Visa/Mastercard).

## 5. Integración con otros Productos y Servicios
El sistema de débitos automáticos interactúa con diversas áreas operativas del banco:

### 5.1. Programa de Fidelidad Volar
Es un dato crítico para el usuario que los débitos automáticos realizados a través de **tarjetas de débito** no generan millas en el programa Volar. Solo los consumos presenciales o manuales en dispositivos POS son elegibles para la acumulación de este beneficio.

### 5.2. Ecosistema de Seguros
La gran mayoría de las pólizas intermediadas por Itaú (Vida, Accidentes Personales, Hogar, Salud y Finanzas) permiten el pago de primas mensuales mediante el débito automático en cuenta corriente o caja de ahorro.

### 5.3. Préstamos Amortizables
En los procesos de renovación de préstamos online, se establece que el pago de la primera cuota del nuevo crédito se realizará obligatoriamente mediante débito automático de la cuenta del cliente al mes siguiente de la acreditación.

### 5.4. Canal WhatsApp (Clientes Paquetizados)
Los clientes con Paquetes (incluyendo Personal Bank) pueden utilizar el **Canal WhatsApp** para solicitar instrucciones de pago de sus tarjetas de crédito mediante débitos directos de sus cuentas bancarias.

## 6. Requisitos de Operación
Para garantizar el éxito de la transacción programada, el sistema requiere:
1.  **Disponibilidad de Fondos:** La cuenta seleccionada debe tener saldo líquido suficiente en la fecha exacta del vencimiento.
2.  **Instrumento Activo:** El medio de pago (cuenta o tarjeta) debe estar plenamente operativo y no bloqueado por razones de seguridad o falta de actualización.