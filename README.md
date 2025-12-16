# Aufgabe REST: LIDAR Scanner

Erstelle einen RESTful Webservice, der eine Punktwolke (x, y, z) als Payload empfängt, und den Abstand des nähesten Punktes zurück gibt, falls er weniger als 10 Units entfernt ist.

## Request-reply

```sh
$ echo '[{"x": 10.0, "y": 12.3, "z": 5.5}]' | http localhost:8080/api/v1/points
HTTP/1.1 200 OK
Content-length: 4
Content-type: application/json
Date: Sun, 14 Dec 2025 12:21:09 GMT

null


$ echo '[{"x": 0.0, "y": 2.3, "z": 5.5}]' | http localhost:8080/api/v1/points
HTTP/1.1 200 OK
Content-length: 17
Content-type: application/json
Date: Sun, 14 Dec 2025 12:21:21 GMT

5.961543424315552
```

## Active Polling

POST-Direction:

```fish
function req -a x y z
  echo "{\"points\": [{\"x\": $x, \"y\": $y, \"z\": $z}]}" | http localhost:8080/api/v1/point-clouds
end
while true
  req (random 0 10) (random 0 10) (random 0 10)
  sleep 1
end
```

GET-Direction:

```fish
while true
  http localhost:8080/api/v1/point-clouds
  sleep 1
end
```