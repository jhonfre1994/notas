import { TestBed } from '@angular/core/testing';

import { RegistrarNotasService } from './registrar-notas.service';

describe('RegistrarNotasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegistrarNotasService = TestBed.get(RegistrarNotasService);
    expect(service).toBeTruthy();
  });
});
