Diseñar una aplicación web, empleando todas las tecnologías vistas en clase, sin incluir MongoDB, que haciendo uso de la base de datos municipios, permita realizar lo siguiente:

1) Dada una comunidad y una provincia, listar los municipios que la componen (provincia). Emplear listas desplegables enlazadas.

2) Dada una provincia (lista desplegable), buscar mediante un cuadro de texto (con autocompletar) el nombre del municipio, y se mostrarán el nombre y la comunidad a la que pertenece.

3) Añadir un municipio.

4) Poder cambiar el nombre de un municipio. La manera para localizar el nombre del municipio a cambiar se deja en manos del programador.

Datasource:

                <xa-datasource jndi-name="java:jboss/datasources/dsmunicipios" pool-name="dsmunicipios" enabled="true" use-ccm="true">
                    <xa-datasource-property name="ServerName">
                        localhost
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                        municipios
                    </xa-datasource-property>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
                </xa-datasource>