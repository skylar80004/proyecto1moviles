# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2019_04_23_031049) do

  create_table "active_storage_attachments", force: :cascade do |t|
    t.string "name", null: false
    t.string "record_type", null: false
    t.integer "record_id", null: false
    t.integer "blob_id", null: false
    t.datetime "created_at", null: false
    t.index ["blob_id"], name: "index_active_storage_attachments_on_blob_id"
    t.index ["record_type", "record_id", "name", "blob_id"], name: "index_active_storage_attachments_uniqueness", unique: true
  end

  create_table "active_storage_blobs", force: :cascade do |t|
    t.string "key", null: false
    t.string "filename", null: false
    t.string "content_type"
    t.text "metadata"
    t.bigint "byte_size", null: false
    t.string "checksum", null: false
    t.datetime "created_at", null: false
    t.index ["key"], name: "index_active_storage_blobs_on_key", unique: true
  end

  create_table "comments", force: :cascade do |t|
    t.text "comment", null: false
    t.integer "restaurant_id", null: false
  end

  create_table "photos", force: :cascade do |t|
    t.text "description", null: false
    t.integer "restaurant_id", null: false
  end

  create_table "prices", force: :cascade do |t|
    t.string "description", null: false
  end

  create_table "restaurants", force: :cascade do |t|
    t.string "name", null: false
    t.float "stars"
    t.float "latitude"
    t.float "longitude"
    t.string "kind_food", null: false
    t.integer "price_id", null: false
    t.string "phone", null: false
    t.string "website", null: false
  end

  create_table "schedules", force: :cascade do |t|
    t.string "day", null: false
    t.time "start", null: false
    t.time "end", null: false
    t.integer "restaurant_id", null: false
  end

  create_table "users", force: :cascade do |t|
    t.string "name", null: false
    t.string "last_name", null: false
    t.string "username", null: false
    t.string "password", null: false
    t.string "email", null: false
  end

end
