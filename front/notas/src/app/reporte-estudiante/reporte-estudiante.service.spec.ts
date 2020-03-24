import { TestBed } from '@angular/core/testing';

import { ReporteEstudianteService } from './reporte-estudiante.service';

describe('ReporteEstudianteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReporteEstudianteService = TestBed.get(ReporteEstudianteService);
    expect(service).toBeTruthy();
  });
});
