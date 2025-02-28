Probar mediante CURL

1️⃣ Crear un Préstamo

```=json
curl -X POST http://localhost:8080/prestamos \
     -H "Content-Type: application/json" \
     -d '{
           "id": "1",
           "monto": 1000.0,
           "clienteId": "123",
           "fecha": "2025-02-28",
           "estado": "PENDIENTE"
         }'

```

2️⃣ Obtener Todos los Préstamos

```=json
curl -X GET http://localhost:8080/prestamos

```
3️⃣ Obtener Solo los Préstamos Activos

```=json
curl -X GET http://localhost:8080/prestamos/activos

```

4️⃣ Obtener un Préstamo por ID
```=json
curl -X GET http://localhost:8080/prestamos/1

```
5️⃣ Actualizar Estado del Préstamo a PAGADO


```=json
curl -X PUT "http://localhost:8080/prestamos/1/estado?estado=PAGADO"

```

6️⃣  Calcular el Monto Total a Pagar con Intereses
Para Cliente VIP (5% de interés)

```=json
curl -X GET "http://localhost:8080/prestamos/1/calculo?tipoCliente=VIP"
```

Para Cliente REGULAR (10% de interés)
```=json
curl -X GET "http://localhost:8080/prestamos/1/calculo?tipoCliente=REGULAR"

```
Eliminar un Préstamo por ID
```=json
curl -X DELETE http://localhost:8080/prestamos/1

```


![Screenshot from 2025-02-28 13-32-43](https://github.com/user-attachments/assets/eb211e90-2b61-40ed-ab01-0bc4eafeeff1)
