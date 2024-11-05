import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetOcorrenciaComponent } from './pet-ocorrencia.component';

describe('PetOcorrenciaComponent', () => {
  let component: PetOcorrenciaComponent;
  let fixture: ComponentFixture<PetOcorrenciaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PetOcorrenciaComponent]
    });
    fixture = TestBed.createComponent(PetOcorrenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
