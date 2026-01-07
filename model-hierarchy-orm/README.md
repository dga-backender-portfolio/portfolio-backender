# Guía de Estrategias de Herencia en JPA / Hibernate

Este documento describe las tres estrategias principales para mapear jerarquías de clases Java a bases de datos relacionales, analizando sus casos de uso y rendimiento.

---

## 1. Single Table (TPH - Table Per Hierarchy)
Es la opción por defecto. Toda la jerarquía se almacena en una **única tabla**.

* **Mecanismo:** Usa una `@DiscriminatorColumn` para diferenciar el tipo de objeto.
* **Pros:** Máximo rendimiento en lecturas (sin JOINs).
* **Contras:** Obliga a que las columnas de las subclases sean `NULLABLE`.
* **Uso:** Jerarquías simples con pocos atributos diferenciados.



---

## 2. Joined Table (TPT - Table Per Type)
Sigue el principio de **normalización**. Cada clase tiene su propia tabla.

* **Mecanismo:** La tabla base guarda campos comunes y las hijas los específicos, conectadas por Primary Key / Foreign Key.
* **Pros:** Integridad de datos total (permite `NOT NULL`) y diseño de BD limpio.
* **Contras:** Penalización en lecturas por el uso intensivo de `JOINs`.
* **Uso:** Modelos de datos complejos donde la integridad es crítica.



---

## 3. Table Per Class
Cada clase concreta tiene su propia tabla que **contiene todos los campos** (propios y heredados).

* **Mecanismo:** La tabla de la subclase es independiente y duplica las columnas de la clase base.
* **Pros:** Consultas muy rápidas si solo se busca una subclase específica (no hay JOINs).
* **Contras:** Las consultas polimórficas (sobre la clase padre) usan `UNION ALL`, lo que escala muy mal. No hay una tabla base física para relaciones externas.
* **Uso:** Raro. Solo cuando la herencia es meramente estructural en Java pero las tablas son lógicamente independientes en BD.



---

## Comparativa Final de Estrategias

| Característica | Single Table (TPH) | Joined Table (TPT) | Table Per Class |
| :--- | :--- | :--- | :--- |
| **Anotación** | `@Inheritance(strategy = ...SINGLE_TABLE)` | `@Inheritance(strategy = ...JOINED)` | `@Inheritance(strategy = ...TABLE_PER_CLASS)` |
| **Rendimiento Lectura** | 🚀 Excelente | 🐢 Lento (JOINs) | ⚡ Rápido (Subclase) / 🐌 Lento (Padre) |
| **Normalización** | ❌ Nula | ✅ Alta | ❌ Baja (Duplicación) |
| **Integridad (NOT NULL)**| No permitida | Sí permitida | Sí permitida |
| **Consultas Padre** | `SELECT FROM Table` | `JOIN