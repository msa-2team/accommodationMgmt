package roomreservation;

import javax.persistence.*;

@Entity
@Table(name="ManagementList_table")
public class ManagementList {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

}
