/*
 * Copyright 2013 satrapu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.satrapu.homebudget.services.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "Currencies", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Name"}),
    @UniqueConstraint(columnNames = {"ThreeLettersIsoName"})})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Currency extends ManagedEntity {

    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 200, name = "Name")
    private String name;
    @NotNull
    @Size(min = 3, max = 3)
    @Column(nullable = false, length = 3, name = "ThreeLettersIsoName")
    private String threeLettersIsoName;
}
