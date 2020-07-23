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
