import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError } from 'rxjs';
import { ServiceService } from 'src/app/services/service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detalhes',
  templateUrl: './detalhes.component.html',
  styleUrls: ['./detalhes.component.css']
})


export class DetalhesComponent {
  pet: any;
  pets: any
  isEditing = false; 
  pet_image!: string;
  user_id!: string;
  originalImageUrl: string = ''; 
  image: any;
  constructor(
    private route: ActivatedRoute,
    private petsService: ServiceService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    const petId = Number(this.route.snapshot.paramMap.get('id'));
   
    this.listPets(petId);
  }

  listPets(petId: any){
    this.petsService.getPetsByMat(petId).subscribe((resp: any) => {
      this.pet = resp;
      this.image = resp.petImage;
    });
  }

  voltar(): void {
    this.router.navigate(['/ocorrencias']);
  }

  editPet(): void {
    this.isEditing = true;
  }

  onSubmit(): void {
    this.updatePet(
      this.pet.id,
      this.pet.txPet, 
      this.pet.endereco, 
      this.pet.txSx,
      this.pet.txStatus,
      this.pet.txObs, 
      this.pet.userId);
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result as string;
      };
      reader.readAsDataURL(file);
    } else {
      console.log('Nenhuma nova imagem selecionada. Mantendo a imagem existente:', this.image);
    }
  }
  

  getCurrentLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const coords = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };

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
        this.pet.endereco = `${street}, ${houseNumber},  ${postcode}, ${suburb}, ${state}`;
      } else {
        console.error('Endereço não encontrado');
        this.pet.endereco = 'Endereço não encontrado';
      }
    }, (error: any) => {
      console.error('Erro ao converter coordenadas em endereço: ', error);
      this.pet.endereco = 'Erro ao obter endereço';
    });
  }
  
  updatePet(
    id: number,
    pet: string,
    gender: string,
    address: string,
    status: string,
    observation: string,
    user_id: string,
  ){
    this.petsService.updatePet( 
      this.pet.id,   
      this.pet.txPet, 
      this.pet.txSx, 
      this.pet.endereco, 
      this.pet.txStatus,
      this.pet.txObs,
      this.image,
      this.pet.userId ).pipe(
        catchError(error => {
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: `${error.error.message}`,
          })
          return ''
        })
      ).subscribe(resp => {
        this.isEditing = false;
        Swal.fire({
          icon: 'success',
          title: 'Sucesso!',
          text: 'Ocorrencia Atualizada com sucesso!',
        }).then((result) => {
          // if (result.isConfirmed) {
          //   this.router.navigate(['/home']);
          // }
        });
      })
  }

  deletePet(): void {
    if (confirm('Tem certeza que deseja excluir este pet?')) {
      this.petsService.deletePet(this.pet.id).pipe(
        catchError(error => {
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: `${error.error.message}`,
          })
          return ''
        })
      ).subscribe(resp => {
        Swal.fire({
          icon: 'success',
          title: 'Sucesso!',
          text: 'Ocorrencia realizada com sucesso!',
        }).then((result) => {
          if (result.isConfirmed) {
            this.router.navigate(['/ocorrencias']);
          }
        });
      });
    }
  }

}
