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

El primer usuario registrado tendrá los roles usuario, admin y súperadmin, estando habilitado parar dar y quitar permisos de admin junto con eliminar a los demás usuarios, los admin sólo pueden dar permisos y eliminar a usuarios normales.

El usuario posee los atributos "username" y "email", ambos únicos en la base de datos, además de la contraseña. Para iniciar sesión se requiere el nombre de usuario.
