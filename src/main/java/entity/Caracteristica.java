package entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @NoArgsConstructor
    public class Caracteristica implements Comparable<Caracteristica>, Serializable
    {
        @EqualsAndHashCode.Include
        protected Integer idCaracteristica;
        private String nume;
        private String descriere;

        protected CategorieCaracteristica categorie = CategorieCaracteristica.TEHNICA;
        //
        public Caracteristica(Integer idCaracteristica, String nume, String descriere,
                       CategorieCaracteristica categorie) {
            super();
            this.idCaracteristica = idCaracteristica;
            this.nume = nume;
            this.descriere = descriere;
            this.categorie = categorie;
        }
        public Caracteristica(Integer idCaracteristica, String nume, String descriere) {
            super();
            this.idCaracteristica = idCaracteristica;
            this.nume = nume;
            this.descriere = descriere;
        }
        public Caracteristica(Integer idCaracteristica, String nume) {
            super();
            this.idCaracteristica = idCaracteristica;
            this.nume = nume;
        }





        //
        public enum CategorieCaracteristica {
            BUSINESS, TEHNICA;
        }

        @Override
        public int compareTo(Caracteristica other) {
            if (this.equals(other))
                return 0;
            return this.getNume().compareTo(other.getNume());
        }

        Comparable<Object> getNume() {
            return null;
        }

        @Override
        public String toString() {
            return "\n\t\tCaracteristica [idCaracteristica=" + idCaracteristica + ", nume=" + nume
                    + ", descriere=" + descriere + ", categorie=" + categorie + "]";
        }
    }
