import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomCarItemComponent } from './custom-car-item.component';

describe('CustomCarItemComponent', () => {
  let component: CustomCarItemComponent;
  let fixture: ComponentFixture<CustomCarItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomCarItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomCarItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
