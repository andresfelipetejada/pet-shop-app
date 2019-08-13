import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { Category } from 'src/app/model/category';
import { GenericService } from 'src/app/service/generic.service';

@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styleUrls: ['./admin-category.component.scss']
})
export class AdminCategoryComponent implements OnInit {

  displayedColumns: string[] = ['name', 'description', 'urlImage', 'created_at', 'update_at', 'edit', 'delete'];
  dataSource = new MatTableDataSource<any>();

  selected: Category = new Category();
  loading = false;

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(public service: GenericService) {

  }

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.refresh();
  }

  async refresh() {
    this.loading = true;
    const data = await this.service.getAll('category');
    this.dataSource.data = data;
    this.loading = false;
  }

  async update() {
    if (this.selected.id !== undefined) {
      await this.service.update('category', this.selected.id, this.selected);
    } else {
      await this.service.create('category', this.selected);
    }
    this.selected = new Category();
    await this.refresh();
  }

  edit(category: Category) {
    this.selected = category;
  }

  clear() {
    this.selected = new Category();
  }

  async delete(category: Category) {
    this.loading = true;
    if (confirm(`Are you sure you want to delete the category ${category.name}. This cannot be undone.`)) {
      await this.service.delete('category', category.id);
    }
    await this.refresh();
  }

}
