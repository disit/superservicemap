mvn install:install-file \
   -Dfile=lib/virtjdbc4-1.0.jar \
   -DgroupId=virtuoso \
   -DartifactId=virtjdbc4 \
   -Dversion=1.0 \
   -Dpackaging=jar \
   -DgeneratePom=true

mvn install:install-file \
   -Dfile=lib/virt-rdf4j-1.0.jar \
   -DgroupId=virtuoso \
   -DartifactId=virt-rdf4j \
   -Dversion=1.0 \
   -Dpackaging=jar \
   -DgeneratePom=true
