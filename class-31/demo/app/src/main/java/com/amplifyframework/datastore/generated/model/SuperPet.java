package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the SuperPet type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SuperPets", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byOwners", fields = {"superOwnerId","name"})
public final class SuperPet implements Model {
  public static final QueryField ID = field("SuperPet", "id");
  public static final QueryField NAME = field("SuperPet", "name");
  public static final QueryField TYPE = field("SuperPet", "type");
  public static final QueryField HEIGHT = field("SuperPet", "height");
  public static final QueryField SUPER_OWNER = field("SuperPet", "superOwnerId");
  public static final QueryField S3_IMAGE_KEY = field("SuperPet", "s3ImageKey");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="SuperPetTypeEnum") SuperPetTypeEnum type;
  private final @ModelField(targetType="Int") Integer height;
  private final @ModelField(targetType="SuperOwner") @BelongsTo(targetName = "superOwnerId", type = SuperOwner.class) SuperOwner superOwner;
  private final @ModelField(targetType="String") String s3ImageKey;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public SuperPetTypeEnum getType() {
      return type;
  }
  
  public Integer getHeight() {
      return height;
  }
  
  public SuperOwner getSuperOwner() {
      return superOwner;
  }
  
  public String getS3ImageKey() {
      return s3ImageKey;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private SuperPet(String id, String name, SuperPetTypeEnum type, Integer height, SuperOwner superOwner, String s3ImageKey) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.height = height;
    this.superOwner = superOwner;
    this.s3ImageKey = s3ImageKey;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      SuperPet superPet = (SuperPet) obj;
      return ObjectsCompat.equals(getId(), superPet.getId()) &&
              ObjectsCompat.equals(getName(), superPet.getName()) &&
              ObjectsCompat.equals(getType(), superPet.getType()) &&
              ObjectsCompat.equals(getHeight(), superPet.getHeight()) &&
              ObjectsCompat.equals(getSuperOwner(), superPet.getSuperOwner()) &&
              ObjectsCompat.equals(getS3ImageKey(), superPet.getS3ImageKey()) &&
              ObjectsCompat.equals(getCreatedAt(), superPet.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), superPet.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getType())
      .append(getHeight())
      .append(getSuperOwner())
      .append(getS3ImageKey())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SuperPet {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("height=" + String.valueOf(getHeight()) + ", ")
      .append("superOwner=" + String.valueOf(getSuperOwner()) + ", ")
      .append("s3ImageKey=" + String.valueOf(getS3ImageKey()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static SuperPet justId(String id) {
    return new SuperPet(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      type,
      height,
      superOwner,
      s3ImageKey);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    SuperPet build();
    BuildStep id(String id);
    BuildStep type(SuperPetTypeEnum type);
    BuildStep height(Integer height);
    BuildStep superOwner(SuperOwner superOwner);
    BuildStep s3ImageKey(String s3ImageKey);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private SuperPetTypeEnum type;
    private Integer height;
    private SuperOwner superOwner;
    private String s3ImageKey;
    @Override
     public SuperPet build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SuperPet(
          id,
          name,
          type,
          height,
          superOwner,
          s3ImageKey);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep type(SuperPetTypeEnum type) {
        this.type = type;
        return this;
    }
    
    @Override
     public BuildStep height(Integer height) {
        this.height = height;
        return this;
    }
    
    @Override
     public BuildStep superOwner(SuperOwner superOwner) {
        this.superOwner = superOwner;
        return this;
    }
    
    @Override
     public BuildStep s3ImageKey(String s3ImageKey) {
        this.s3ImageKey = s3ImageKey;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, SuperPetTypeEnum type, Integer height, SuperOwner superOwner, String s3ImageKey) {
      super.id(id);
      super.name(name)
        .type(type)
        .height(height)
        .superOwner(superOwner)
        .s3ImageKey(s3ImageKey);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder type(SuperPetTypeEnum type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder height(Integer height) {
      return (CopyOfBuilder) super.height(height);
    }
    
    @Override
     public CopyOfBuilder superOwner(SuperOwner superOwner) {
      return (CopyOfBuilder) super.superOwner(superOwner);
    }
    
    @Override
     public CopyOfBuilder s3ImageKey(String s3ImageKey) {
      return (CopyOfBuilder) super.s3ImageKey(s3ImageKey);
    }
  }
  
}
