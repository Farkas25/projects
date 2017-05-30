using UnityEngine;
using System.Collections;

public class SunorMoonChange : MonoBehaviour {
    public GameObject obj;
    private float targetX;
    private bool inCamera;
	// Use this for initialization
	void Start () {
        targetX = -21;
    }
	
	// Update is called once per frame
	void Update () {
        float dt = Time.deltaTime;

        if (Input.GetKey(KeyCode.Space))
        {
            if (transform.position.x >= -30)
                inCamera = true;
            else
                inCamera = false;
        }

        if (obj.transform.position.x <= -35 && transform.position.x <= targetX && !inCamera)
            transform.position = transform.position + Vector3.right * 10.0f * dt;
    }
}
