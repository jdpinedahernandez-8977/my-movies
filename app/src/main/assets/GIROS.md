# Documentación Técnica: Giros a IEDE - Banco Itaú Uruguay

Este archivo ha sido diseñado para proporcionar una descripción técnica y procedimental exhaustiva de la funcionalidad **Giros a IEDE**, optimizada para su procesamiento por modelos de inteligencia artificial y la comprensión profunda de los protocolos de transferencia electrónica en el ecosistema financiero de Uruguay.

## 1. Definición Conceptual
Los giros a IEDE (Instituciones Emisoras de Dinero Electrónico) se definen como transferencias de fondos dirigidas a entidades financieras que, aunque no son bancos tradicionales, operan bajo una lógica y funciones similares a estos. Estas instituciones permiten la gestión de dinero electrónico a través de tarjetas o billeteras digitales.

## 2. Protocolo de Ejecución Operativa
A diferencia de las transferencias comunes realizadas dentro de la plataforma Itaú Link (ya sea entre cuentas propias o hacia bancos locales), los giros a IEDE poseen un módulo de acceso específico en la interfaz web.

### Requisitos de Acceso y Navegación
1.  **Autenticación:** El usuario debe ingresar al sitio oficial del banco utilizando su número de documento y contraseña personal.
2.  **Ruta Técnica:** La secuencia de navegación en el menú principal es: **Productos y Servicios** > **Cuentas** > **Giros a IEDE**.

### Parámetros de la Transacción
Para formalizar el giro, el sistema requiere la configuración de los siguientes campos de datos:
*   **Cuenta de Origen:** Selección de la cuenta desde la cual se debitarán los fondos.
*   **IEDE Beneficiaria:** El sistema permite elegir entre las entidades actualmente integradas: **OCABlue**, **RedPagos** o **PREX**.
*   **Datos del Receptor:** Se debe ingresar con precisión el número de cuenta y el nombre del beneficiario.
*   **Referencia:** Descripción o concepto del pago para el registro de la operación.

## 3. Ciclos de Procesamiento y Liquidación
La temporalidad de la acreditación de estos giros sigue el mismo protocolo estándar que las transferencias dirigidas a otros bancos de plaza locales.
*   **Corte Horario:** Las solicitudes realizadas después de las **16:00 h** no se procesan de forma inmediata.
*   **Efectividad:** Los giros efectuados fuera del horario de corte o en días inhábiles son procesados y liquidados el **siguiente día hábil**.

## 4. Contexto y Relaciones del Sistema
La funcionalidad de Giros a IEDE se encuentra integrada dentro del módulo general de **Cuentas** y está técnicamente relacionada con los servicios de **Transferencias** y **Tarjetas de Débito** del banco,. Esta herramienta expande la interoperabilidad del banco con otros actores del sistema de pagos electrónicos nacional,.