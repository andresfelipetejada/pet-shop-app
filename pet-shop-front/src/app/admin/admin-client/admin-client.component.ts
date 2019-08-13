import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { Client } from 'src/app/model/client';
import { GenericService } from 'src/app/service/generic.service';

@Component({
  selector: 'app-admin-client',
  templateUrl: './admin-client.component.html',
  styleUrls: ['./admin-client.component.scss']
})
export class AdminClientComponent  implements OnInit {

  displayedColumns: string[] = ['cedula', 'nombres', 'apellidos', 'telefono', 'email', 'created_at', 'update_at', 'edit', 'delete'];
  dataSource = new MatTableDataSource<any>();

  selected: Client = new Client();
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
    const data = await this.service.getAll('client');
    this.dataSource.data = data;
    this.loading = false;
  }

  async update() {
    if (this.selected.id !== undefined) {
      await this.service.update('client', this.selected.id, this.selected);
    } else {
      await this.service.create('client', this.selected);
    }
    this.selected = new Client();
    await this.refresh();
  }

  edit(client: Client) {
    this.selected = client;
  }

  clear() {
    this.selected = new Client();
  }

  async delete(client: Client) {
    this.loading = true;
    if (confirm(`Are you sure you want to delete the client ${client.nombres}. This cannot be undone.`)) {
      await this.service.delete('client', client.id);
    }
    await this.refresh();
  }

}
