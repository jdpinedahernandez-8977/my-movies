# Documentación Técnica: Sistema de Transferencias - Banco Itaú Uruguay

Este archivo `.md` ha sido estructurado por un especialista en la generación de datos para modelos de inteligencia artificial, proporcionando una arquitectura detallada sobre los protocolos, tipos y medidas de seguridad del sistema de transferencias de Banco Itaú Uruguay, basada exclusivamente en las fuentes oficiales proporcionadas.

## 1. Canales y Medios de Transferencia
El banco dispone de múltiples interfaces para la ejecución de movimientos de fondos, tanto físicas como digitales.
*   **Presencial:** Disponibilidad de realizar transferencias en cualquier sucursal del banco.
*   **Digital:** Operativa online a través de la web oficial (Itaú Link) y la aplicación móvil (App Itaú).
*   **WhatsApp:** Los clientes con "Paquetes" (como Personal Bank) pueden efectuar transferencias mediante el **Canal WhatsApp**, previa habilitación y registro del dispositivo en Itaú Link.

## 2. Clasificación de Transferencias por Destino y Tiempos
La velocidad de acreditación y los costos varían según la entidad receptora de los fondos.

| Tipo de Transferencia | Plazo de Acreditación | Observaciones |
| :--- | :--- | :--- |
| **Entre cuentas Itaú** | Inmediata. | Funcionan las 24 horas, todos los días (hábitos e inhábiles). |
| **A bancos locales** | Entre 24 y 72 horas. | Si se realizan después de las 16:00 h o en día inhábil, se procesan el siguiente día hábil. |
| **Al exterior** | Similar a bancos locales. | Poseen un costo adicional de **20 dólares** por transacción, sujeto a comisiones del banco beneficiario. |
| **Giros a IEDE** | Similar a bancos locales. | Dirigidos a entidades como **OCABlue, RedPagos o PREX**; se procesan al siguiente día hábil si se hacen tras las 16:00 h. |

## 3. Modalidades Operativas Especiales
El sistema permite automatizar movimientos de fondos bajo dos lógicas distintas:
*   **Transferencias Programadas:** Permiten elegir una fecha específica a futuro para la ejecución, sin importar si es un día hábil o inhábil.
*   **Transferencias Recurrentes:** Funcionan como las programadas pero con repetición cíclica (diaria, semanal, mensual o anual). Actualmente, esta modalidad solo está disponible para transferencias entre cuentas de Itaú.

## 4. Protocolos de Validación y Seguridad
Para garantizar la integridad de las transacciones digitales, el banco implementa métodos de validación obligatorios según el monto.

### 4.1. iToken (Clave Digital)
*   **Uso:** Es una clave digital necesaria para validar transferencias tanto en la app como en la web a partir de **U$S 50** o su equivalente en pesos.
*   **Adhesión:** Se habilita exclusivamente desde la **App Itaú**.
*   **Restricción:** Por seguridad, solo puede estar activo en un celular y asociado a una sola cédula de identidad.

### 4.2. Token Físico
*   **Requisito:** Es obligatorio para clientes que posean un límite de transferencias superior a **10.001 dólares**.
*   **Mantenimiento:** Si no se utiliza con frecuencia, el dispositivo se deshabilita automáticamente, requiriendo su baja en la web y la solicitud de un nuevo ejemplar.

## 5. Gestión de Errores y Reclamos
En caso de introducir datos incorrectos (número de cuenta o nombre del beneficiario), se aplican los siguientes protocolos:
*   **Plazo de Reclamo:** Se debe aguardar un periodo de **48 horas** desde el procesamiento de la transferencia para iniciar una queja formal.
*   **Políticas de Devolución:** Dependen del banco beneficiario; algunos devuelven los fondos si el nombre no coincide con el número de cuenta, mientras que otros acreditan el dinero si el número de cuenta es válido aunque el nombre difiera.
*   **Asesoramiento:** Se recomienda contactar al **1784** ante estas incidencias.

## 6. Procedimiento para Transferencias en la Web
1.  Ingresar a `itau.com.uy` con documento y contraseña.
2.  Seleccionar la opción **"Transferencias"** en la barra lateral.
3.  Elegir el tipo de destino (Itaú, bancos locales o exterior).
4.  Completar los campos de cuenta de origen, destino, moneda, importe y referencia.
5.  Validar la operación mediante el método de seguridad correspondiente (**iToken** o **Token físico**).