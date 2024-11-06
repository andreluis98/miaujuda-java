import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../services/service.service';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-pet-ocorrencia',
  templateUrl: './pet-ocorrencia.component.html',
  styleUrls: ['./pet-ocorrencia.component.css']
})
export class PetOcorrenciaComponent {
  pets: any = {};

  name!: string;
  pet!: string;
  address!: string;
  gender!: string;
  status!: string;
  pet_image!: string;
  observation!: string;
  user_id!: string;
  userId: string | null = AppComponent.userId;
  client: any

  constructor(private http: HttpClient, private petsService: ServiceService, private router: Router) {}

  ngOnInit() {
    AppComponent.userId = localStorage.getItem('userId');
    this.user_id = AppComponent.userId !== null ? AppComponent.userId : '';
    console.log('User ID na Home:', this.user_id);
}
  getCurrentLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const coords = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };

        // Chamar função para converter coordenadas para endereço
        this.getStreetAddress(coords.lat, coords.lng);

      }, (error) => {
        console.error('Erro ao obter a localização: ', error);
      });
    } else {
      console.error('Navegador não suporta geolocalização.');
    }
  }

  getStreetAddress(latitude: number, longitude: number) {
    const url = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${latitude}&lon=${longitude}`;

    this.http.get<any>(url).subscribe((data: { address: any; }) => {
      if (data && data.address) {
        const address = data.address;
        const street = address.road || 'Rua não encontrada';
        const houseNumber = address.house_number || '';
        const postcode = address.postcode || '';
        const suburb = address.suburb || '';
        const state = address.state || '';
        this.pets.endereco = `${street}, ${houseNumber},  ${postcode}, ${suburb}, ${state}`;
      } else {
        console.error('Endereço não encontrado');
        this.pets.endereco = 'Endereço não encontrado';
      }
    }, (error: any) => {
      console.error('Erro ao converter coordenadas em endereço: ', error);
      this.pets.endereco = 'Erro ao obter endereço';
    });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        // Converte a imagem para Base64 e armazena até 255 caracteres
        this.pet_image = (reader.result as string);
        console.log('Imagem selecionada em Base64 (truncada):', this.pet_image);
      };
      reader.readAsDataURL(file); // Converte a imagem para Base64
    }
  }

  onSubmit() {
    this.createPet(
      this.pets.txpets, 
      this.pets.endereco, 
      this.pets.txSx,
      this.pets.status,
      this.pets.txObs, 
      this.pet_image, 
      this.user_id);
  }

  createPet(
    pet: string,
    gender: string,
    address: string,
    status: string,
    observation: string,
    pet_image: string,
    user_id: string,
  ) {
    this.petsService.createdPet(
      this.pets.txpets, 
      this.pets.txSx, 
      this.pets.endereco, 
      this.pets.status,
      this.pets.txObs, 
      this.pet_image, 
      this.user_id ).pipe(
      catchError(error => {
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: `${error.error.message}`,
        })
        return ''
      })
    ).subscribe(resp => {
      this.client = resp;
      Swal.fire({
        icon: 'success',
        title: 'Sucesso!',
        text: 'Ocorrencia realizada com sucesso!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(['/home']);
        }
      });
    })
  }
  

}
