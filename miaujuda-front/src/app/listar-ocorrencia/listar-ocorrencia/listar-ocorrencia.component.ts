import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/services/service.service';

@Component({
  selector: 'app-listar-ocorrencia',
  templateUrl: './listar-ocorrencia.component.html',
  styleUrls: ['./listar-ocorrencia.component.css']
})
export class ListarOcorrenciaComponent implements OnInit {
  ocorrencias: any[] = [];
  
  constructor(private http: HttpClient, private petsService: ServiceService, private router: Router) {}

  ngOnInit(): void {
    this.carregarOcorrencias();
  }

  carregarOcorrencias() {
    this.petsService.getPetsList().subscribe(data => {
      this.ocorrencias = data.map((ocorrencia: { id: any; txPet: any; txObs: any; petImage: any; }) => ({
        id: ocorrencia.id,
        txPet: ocorrencia.txPet,
        txObs: ocorrencia.txObs,
        petImage: ocorrencia.petImage
      }));
      console.log(this.ocorrencias); // Para depuração
    }, error => {
      console.error('Erro ao carregar ocorrências', error); // Captura de erros
    });
  }
}
