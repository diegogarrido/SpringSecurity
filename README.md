# SpringSecurity
Proyecto de ejemplo con implementación de Spring Security

Dependencias utilizadas

- Spring Boot
- Spring JPA
- Thymeleaf
- PostgresJDBC
- Spring Web
- DevTools
- Spring Security
- BCrypt
- Spring Boot Validation

Se implementa la jerarquia de roles en la clase "com.diegogarrido.configuration.WebSecurityConfig". Esto quiere decir que un SuperAdmin puede acceder a sus url y a todas las que requieran permisos de Admin y User. A su vez, Admin puede acceder a sus url y a las de User. Siendo User el rol mas bajo en la jerarquia.

El primer usuario registrado tendrá el rol súperadmin, estando habilitado parar dar y quitar permisos de admin junto con eliminar a los demás usuarios, los admin sólo pueden dar permisos y eliminar a usuarios normales.

El usuario posee los atributos "username" y "email", ambos únicos en la base de datos, además de la contraseña. Para iniciar sesión se requiere el nombre de usuario.

LICENCIA MIT

Copyright (c) 2021-2022 Diego Garrido

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
