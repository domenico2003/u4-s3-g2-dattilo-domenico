package poject.classDatabase;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {

	@Id
	@GeneratedValue
	private UUID id;
	@Column
	private String titolo;
	@Column
	private LocalDate dataEvento;
	@Column
	private String descrizione;
	@Column
	@Enumerated(EnumType.STRING)
	private EventoTipo tipoEvento;
	@Column
	private int numeroMassimoPartecipanti;

	public Evento(String titolo, LocalDate dataEvento, String descrizione, EventoTipo tipoEvento,
			int numeroMassimoPartecipanti) {

		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}

}
