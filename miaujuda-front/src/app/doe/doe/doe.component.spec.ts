import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoeComponent } from './doe.component';

describe('DoeComponent', () => {
  let component: DoeComponent;
  let fixture: ComponentFixture<DoeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoeComponent]
    });
    fixture = TestBed.createComponent(DoeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
